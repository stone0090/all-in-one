package controller

import (
	"aio-scheduler/client"
	"aio-scheduler/common/response"
	"aio-scheduler/dal/model"
	"aio-scheduler/service"
	"github.com/gin-gonic/gin"
)

func PythonDeploy(context *gin.Context) {
	deployInfo := model.DeployInfo{}
	err := context.ShouldBind(&deployInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	go service.DeployPython(deployInfo)
	context.JSON(200, response.Success(nil))
}

func PythonInvoke(context *gin.Context) {
	rawData, err := context.GetRawData()
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	result, err := client.InvokePython(rawData)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	context.JSON(200, response.Success(result))
}

func PythonHealthCheck(context *gin.Context) {
	result, err := client.HealthCheckPython()
	if err != nil {
		context.String(200, err.Error())
		return
	}
	context.String(200, result)
}
