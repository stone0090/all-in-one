package service

import (
	"aio-scheduler/client"
	"aio-scheduler/common/constants"
	"aio-scheduler/common/request"
	"aio-scheduler/service/global"
	"github.com/labstack/gommon/log"
	"strconv"
	"time"
)

func CheckDeployInfo(deployInfo request.DeployInfo) error {
	return nil
}

func Deploy(deployInfo request.DeployInfo) {

	// 1、校验部署状态
	success := global.CasDeployStatus(constants.Init, constants.Deploying)
	if !success {
		log.Error("部署失败：[" + global.GetDeployStatus() + "]状态不允许部署！")
	}

	// 2、部署节点
	faasPort, _ := strconv.Atoi(global.Enver.FaasPort)
	nodeId2ServicePortMap := make(map[string]int)
	for index, node := range deployInfo.Nodes {
		port := faasPort + index
		log.Infof("正在部署节点[%v]，端口为[%v]", node.Id, port)
		nodeId2ServicePortMap[node.Id] = port
		if node.ProgrammingLanguage == constants.Python {
			deployPython(node, port)
		}
	}

	// 3、健康检查
	healthCheckChan := make(chan bool, len(deployInfo.Nodes))
	for index := range deployInfo.Nodes {
		go healthCheck(faasPort+index, &healthCheckChan)
	}
	defer close(healthCheckChan)
	healthCheckResult := true
	for range deployInfo.Nodes {
		healthCheckResult = healthCheckResult && <-healthCheckChan
	}

	// 5、更新部署状态和执行信息
	if !healthCheckResult {
		log.Error("部署失败：健康检查失败！")
		return
	}
	runInfo := global.NewRunInfo(deployInfo, nodeId2ServicePortMap)
	global.CasDeployStatus(constants.Deploying, constants.Deployed)

	// 6、启动调度任务
	taskSchedule(runInfo)
}

func healthCheck(port int, healthCheckChan *chan bool) {
	defer func() {
		if err := recover(); err != nil {
			log.Error("健康检查失败！" + err.(error).Error())
			*healthCheckChan <- false
		}
	}()
	// 每100毫秒检查一次，最多检查300次，即30秒超时
	for i := 0; i < 300; i++ {
		result, err := client.HealthCheck(strconv.Itoa(port))
		log.Infof("[%v]端口，已检查[%v]次，耗时[%v]毫秒...", port, i+1, (i+1)*100)
		if result == constants.Success && err == nil {
			log.Infof("[%v]端口健康检查成功！", port)
			*healthCheckChan <- true
			return
		}
		time.Sleep(100 * time.Millisecond)
	}
	log.Errorf("[%v]端口健康检查超时！", port)
	*healthCheckChan <- false
}

func taskSchedule(runInfo global.RunInfo) {
	scheduleInfo := runInfo.DeployInfo.ScheduleInfo
	if scheduleInfo.Type == "" ||
		scheduleInfo.Type == constants.ScheduleTypeNone ||
		scheduleInfo.Expression == "" {
		return // 不需要调度
	}
	// 创建任务超时队列
	taskQueue := global.NewTaskQueue(3)
	// 创建定时调度任务
	newScheduleTask(scheduleInfo, func() {
		runContext := newRunContext(runInfo)
		log.Infof("开始执行调度任务，RequestId为: %v", runContext.RequestId)
		taskQueue.AddTaskAndCallback(runContext.RequestId, runContext.StartTime, func() {
			run(runInfo, taskQueue, runContext)
			log.Infof("调度任务执行完成，RequestId为: %v", runContext.RequestId)
		})
	})
}
