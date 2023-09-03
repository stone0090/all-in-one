package controller

import (
	"aio-scheduler/client"
	"aio-scheduler/common/constants"
	"aio-scheduler/common/request"
	"aio-scheduler/common/response"
	"aio-scheduler/service"
	"aio-scheduler/service/global"
	"encoding/json"
	"github.com/gin-gonic/gin"
	"github.com/labstack/gommon/log"
)

func Deploy(context *gin.Context) {
	var deployInfo request.DeployInfo
	err := context.ShouldBind(&deployInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	jsonData, err := json.Marshal(deployInfo)
	log.Infof("正在部署，DeployInfo为: %s", string(jsonData))
	err = service.CheckDeployInfo(deployInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	go service.Deploy(deployInfo)
	context.JSON(200, response.Success(nil))
}

func Invoke(context *gin.Context) {
	rawData, err := context.GetRawData()
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	result, err := client.Invoke("6001", rawData)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	context.JSON(200, response.Success(result))
}

func HealthCheck(context *gin.Context) {
	if global.IsDeployed() {
		context.String(200, constants.Success)
	} else {
		context.String(200, constants.Failure)
	}
}
