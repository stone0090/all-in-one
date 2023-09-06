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

func NewTaskRunContext() model.TaskRunContext {
	runInfo := global.GetRunInfo()
	taskRunContext := model.TaskRunContext{}
	taskRunContext.Status = constants.Init
	taskRunContext.RequestId = utils.NewUuid()
	taskRunContext.StartTime = time.Now().UnixMilli()
	taskRunContext.ReceiveChanMap = make(map[string]chan interface{})
	taskRunContext.SendChanMap = make(map[string]chan interface{})
	taskRunContext.NodeRunContextMap = make(map[string]model.NodeRunContext)
	for _, node := range runInfo.NodeId2NodeMap {
		nodeRunContext := model.NodeRunContext{}
		nodeRunContext.NodeId = node.Id
		nodeRunContext.Status = constants.Init
		taskRunContext.NodeRunContextMap[node.Id] = nodeRunContext
		for _, port := range node.Ports {
			if port.Type == constants.PortTypeInput {
				// 创建接收通道，用于接收前置节点数据
				inPortId := port.Id
				outPortId := runInfo.In2OutPortIdMap[inPortId]
				if outPortId == "" {
					continue
				}
				taskRunContext.ReceiveChanMap[inPortId] = make(chan interface{})
				taskRunContext.SendChanMap[outPortId] = taskRunContext.ReceiveChanMap[port.Id]
			}
		}
	}
	return taskRunContext
}

func Run(taskRunContext model.TaskRunContext) {
	requestId := taskRunContext.RequestId
	log.Infof("RequestId[%v], Start Run...", requestId, taskRunContext)
	runInfo := global.GetRunInfo()
	log.Infof("RequestId[%v], runInfo[%v]", requestId, runInfo)
	taskQueue := global.GetTaskQueue()
	log.Infof("RequestId[%v], taskQueue[%v]", requestId, taskQueue)
	done := make(chan model.TaskRunContext)
	defer func() {
		close(done)
		log.Infof("RequestId[%v], End Run...", requestId)
	}()
	go func() {
		nodeNum := len(runInfo.NodeId2NodeMap)
		log.Infof("RequestId[%v], task has [%v] node, Start go...", requestId, nodeNum)
		allNodeResultChan := make(chan model.NodeRunContext, nodeNum)
		taskRunContext.Status = constants.Running
		taskRunContext.MasterChan = allNodeResultChan
		taskRunContext.InputParamMap = make(map[string]interface{})
		taskRunContext.OutputParamMap = make(map[string]interface{})
		log.Infof("RequestId[%v], taskRunContext[%v]", requestId, taskRunContext)
		defer func() {
			close(allNodeResultChan)
			if taskQueue.Size > 0 {
				taskQueue.RemoveAllTask(requestId)
			}
			taskRunContext.EndTime = time.Now().UnixMilli()
			taskRunContext.Status = getRunStatus(taskRunContext)
			done <- taskRunContext
			log.Infof("RequestId[%v], End go...", requestId, taskRunContext)
		}()
		for _, node := range runInfo.NodeId2NodeMap {
			go runNode(runInfo, taskRunContext, node.Id)
		}
		for i := 0; i < nodeNum; i++ {
			nodeResult := <-allNodeResultChan
			log.Infof("RequestId[%v], node [%v] run finish，result is [%v]", requestId, nodeResult.NodeId, nodeResult)
		}
	}()
	select {
	case <-done:
		taskRunContext.Status = constants.Success
		log.Infof("RequestId[%v], Run Success！", requestId)
		return
	case <-time.After(time.Minute * 1):
		taskRunContext.Status = constants.Failure
		log.Infof("RequestId[%v], Run Failure，Timeout 1 minute！", requestId)
		return
	}
}

func runNode(runInfo global.RunInfo, taskRunContext model.TaskRunContext, nodeId string) {
	node := runInfo.NodeId2NodeMap[nodeId]
	log.Infof("nodeId[%v], Start runNode...", nodeId)
	nodeRunContext := taskRunContext.NodeRunContextMap[nodeId]
	nodeRunContext.StartTime = time.Now().UnixMilli()
	nodeRunContext.Status = constants.Running
	nodeRunContext.InputParamMap = make(map[string]interface{})
	nodeRunContext.OutputParamMap = make(map[string]interface{})
	defer func() {
		if err := recover(); err != nil {
			log.Errorf("nodeId[%v], runNode error: %v", nodeId, err)
		}
		nodeRunContext.EndTime = time.Now().UnixMilli()
		taskRunContext.MasterChan <- nodeRunContext
		log.Infof("nodeId[%v], End runNode: nodeRunContext[%v]", nodeId, nodeRunContext)
	}()
	// 获取输入参数
	for _, port := range node.Ports {
		if port.Type == constants.PortTypeInput {
			var inputData interface{}
			if taskRunContext.ReceiveChanMap[port.Id] != nil {
				// 接受前置节点数据
				inputData = <-taskRunContext.ReceiveChanMap[port.Id]
				log.Infof("nodeId[%v], Receive data from previous node: portId[%v], inputData[%v]", nodeId, port.Id, inputData)
			} else {
				// 没有前置节点，则使用默认值
				inputData = port.OpParamDefaultValue
			}
			nodeRunContext.InputParamMap[port.OpParamCode] = inputData
			taskRunContext.InputParamMap[port.Id+"."+port.OpParamCode] = inputData
		}
	}
	// 执行节点，调用faas接口
	servicePort := strconv.Itoa(runInfo.NodeId2ServicePortMap[nodeId])
	postData, _ := json.Marshal(nodeRunContext.InputParamMap)
	log.Infof("nodeId[%v], Start Invoke: servicePort[%v], postData[%v]", nodeId, servicePort, nodeRunContext.InputParamMap)
	result, err := client.Invoke(servicePort, postData)
	if err != nil {
		log.Errorf("nodeId[%v], Invoke error: %v", nodeId, err)
		nodeRunContext.Status = constants.Failure
		return
	}
	log.Infof("nodeId[%v], End Invoke: servicePort[%v], result[%v]", nodeId, servicePort, result)
	if outputParamMap, ok := result.(map[string]interface{}); ok {
		log.Infof("nodeId[%v], Invoke result is a map: %v", nodeId, outputParamMap)
		nodeRunContext.OutputParamMap = outputParamMap
	} else {
		log.Errorf("nodeId[%v], Invoke result is not a map.", nodeId)
		nodeRunContext.Status = constants.Failure
		return
	}
	// TODO: 需要校验输出参数是否符合要求
	for _, port := range node.Ports {
		if port.Type == constants.PortTypeOutput {
			outputData := nodeRunContext.OutputParamMap[port.OpParamCode]
			if outputData == nil {
				log.Errorf("nodeId[%v], Output data is nil: portId[%v], OpParamCode[%v]", nodeId, port.Id, port.OpParamCode)
				nodeRunContext.Status = constants.Failure
				return
			}
			if taskRunContext.SendChanMap[port.Id] != nil {
				// 将数据传递给后置节点
				taskRunContext.SendChanMap[port.Id] <- outputData
				log.Infof("nodeId[%v], Send data to next node: portId[%v], outputData[%v]", nodeId, port.Id, outputData)
			} else {
				// 没有后置节点，则将结果写道全局变量
				taskRunContext.OutputParamMap[port.Id+"."+port.OpParamCode] = outputData
			}
		}
	}
	nodeRunContext.Status = constants.Success
}

func getRunStatus(taskRunContext model.TaskRunContext) string {
	for _, node := range taskRunContext.NodeRunContextMap {
		if node.Status != constants.Success {
			return constants.Failure
		}
	}
	return constants.Success
}
