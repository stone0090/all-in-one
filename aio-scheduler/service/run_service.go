package service

import (
	"aio-scheduler/client"
	"aio-scheduler/common/constants"
	"aio-scheduler/common/utils"
	"aio-scheduler/service/global"
	"aio-scheduler/service/model"
	"encoding/json"
	"github.com/labstack/gommon/log"
	"strconv"
	"time"
)

func newRunContext(runInfo global.RunInfo) model.RunContext {
	runContext := model.RunContext{}
	runContext.Status = constants.Init
	runContext.RequestId = utils.NewUuid()
	runContext.StartTime = time.Now().UnixMilli()
	runContext.ReceiveChanMap = make(map[string]chan interface{})
	runContext.SendChanMap = make(map[string]chan interface{})
	runContext.NodeContextMap = make(map[string]model.NodeContext)
	for _, node := range runInfo.NodeId2NodeMap {
		nodeContext := model.NodeContext{}
		nodeContext.NodeId = node.Id
		nodeContext.Status = constants.Init
		runContext.NodeContextMap[node.Id] = nodeContext
		for _, port := range node.Ports {
			if port.Type == constants.PortTypeInput {
				// 创建接收通道，用于接收前置节点数据
				runContext.ReceiveChanMap[port.Id] = make(chan interface{})
				fromPortId := runInfo.Out2InPortIdMap[port.Id]
				runContext.SendChanMap[fromPortId] = runContext.ReceiveChanMap[port.Id]
			}
		}
	}
	return runContext
}

func run(runInfo global.RunInfo, taskQueue global.TaskQueue, runContext model.RunContext) {
	done := make(chan model.RunContext)
	defer close(done)
	go func() {
		nodeNum := len(runInfo.NodeId2NodeMap)
		allNodeResultChan := make(chan model.NodeContext, nodeNum)
		runContext.Status = constants.Running
		runContext.MasterChan = allNodeResultChan
		defer func() {
			close(allNodeResultChan)
			taskQueue.RemoveAllTask(runContext.RequestId)
			runContext.EndTime = time.Now().UnixMilli()
			runContext.Status = getRunStatus(runContext)
			done <- runContext
		}()
		for _, node := range runInfo.NodeId2NodeMap {
			go runNode(runInfo, runContext, node.Id)
		}
		for i := 0; i < nodeNum; i++ {
			nodeResult := <-allNodeResultChan
			log.Infof("节点[%v]执行完成，执行结果为[%v]", nodeResult.NodeId, nodeResult)
		}
	}()
	select {
	case <-done:
		runContext.Status = constants.Success
		log.Info("执行成功！")
		return
	case <-time.After(time.Minute * 1):
		runContext.Status = constants.Failure
		log.Info("执行失败，运行时间超过1分钟！")
		return
	}
}

func runNode(runInfo global.RunInfo, runContext model.RunContext, nodeId string) {
	node := runInfo.PortId2NodeMap[nodeId]
	nodeContext := runContext.NodeContextMap[nodeId]
	nodeContext.StartTime = time.Now().UnixMilli()
	nodeContext.Status = constants.Running
	defer func() {
		if err := recover(); err != nil {
			log.Errorf("runNode error: %v", err)
		}
		for _, port := range node.Ports {
			if port.Type == constants.PortTypeInput {
				close(runContext.ReceiveChanMap[port.Id])
			}
			if port.Type == constants.PortTypeOutput {
				close(runContext.SendChanMap[port.Id])
			}
		}
		nodeContext.EndTime = time.Now().UnixMilli()
		runContext.MasterChan <- nodeContext
	}()
	// 获取输入参数
	for _, port := range node.Ports {
		if port.Type == constants.PortTypeInput {
			var inputData interface{}
			if runContext.ReceiveChanMap[port.Id] != nil {
				// 接受前置节点数据
				inputData = <-runContext.ReceiveChanMap[port.Id]
			} else {
				// 没有前置节点，则使用默认值
				inputData = port.OpParamDefaultValue
			}
			nodeContext.InputParamMap[port.OpParamCode] = inputData
			runContext.InputParamMap[port.Id+"."+port.OpParamCode] = inputData
		}
	}
	// 执行节点，调用faas接口
	servicePort := strconv.Itoa(runInfo.NodeId2ServicePortMap[node.Id])
	postData, _ := json.Marshal(nodeContext.InputParamMap)
	log.Infof("Start Invoke: servicePort[%v], postData[%v]", servicePort, postData)
	result, err := client.Invoke(servicePort, postData)
	if err != nil {
		log.Errorf("Invoke error: %v", err)
		nodeContext.Status = constants.Failure
		return
	}
	log.Infof("End Invoke: servicePort[%v], result[%v]", servicePort, result)
	if outputParamMap, ok := result.(map[string]interface{}); ok {
		nodeContext.OutputParamMap = outputParamMap
	} else {
		log.Error("Invoke result is not a map.")
		nodeContext.Status = constants.Failure
		return
	}
	// TODO: 需要校验输出参数是否符合要求
	for _, port := range node.Ports {
		if port.Type == constants.PortTypeOutput {
			outputData := nodeContext.OutputParamMap[port.OpParamCode]
			if runContext.SendChanMap[port.Id] != nil {
				// 将数据传递给后置节点
				runContext.SendChanMap[port.Id] <- outputData
			} else {
				// 没有后置节点，则将结果写道全局变量
				runContext.OutputParamMap[port.Id+"."+port.OpParamCode] = outputData
			}
		}
	}
	nodeContext.Status = constants.Success
}

func getRunStatus(runContext model.RunContext) string {
	for _, node := range runContext.NodeContextMap {
		if node.Status != constants.Success {
			return constants.Failure
		}
	}
	return constants.Success
}
