package controller

import (
	"aio-scheduler/client"
	"aio-scheduler/common/constants"
	"aio-scheduler/common/response"
	"aio-scheduler/dal/model"
	"aio-scheduler/service"
	"aio-scheduler/service/global"
	"github.com/gin-gonic/gin"
)

func Deploy(context *gin.Context) {
	var deployInfo model.DeployInfo
	err := context.ShouldBind(&deployInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	if len(deployInfo.Nodes) == 0 {
		context.JSON(200, response.Failure("节点信息不能为空！"))
		return
	}
	go service.Deploy(deployInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
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
