package service

import (
	"aio-scheduler/client"
	"aio-scheduler/common/constants"
	"aio-scheduler/dal/model"
	"aio-scheduler/service/global"
	"github.com/labstack/gommon/log"
	"strconv"
	"time"
)

func Deploy(deployInfo model.DeployInfo) {

	// 1、校验部署状态
	success := global.CasDeployStatus(constants.Init, constants.Deploying)
	if !success {
		log.Error("部署失败：[" + global.GetDeployStatus() + "]状态不允许部署！")
	}

	// 2、部署节点
	faasPort, _ := strconv.Atoi(global.Enver.FaasPort)
	for index, node := range deployInfo.Nodes {
		port := faasPort + index
		log.Infof("正在部署节点[%v]，端口为[%v]", node.NodeId, port)
		if node.Language == constants.Python {
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
	if !healthCheckResult {
		log.Error("部署失败：健康检查失败！")
	}

	// 4、更新部署状态
	global.CasDeployStatus(constants.Deploying, constants.Deployed)
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
