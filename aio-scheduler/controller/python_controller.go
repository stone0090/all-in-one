package controller

import (
	"aio-scheduler/common/response"
	"aio-scheduler/dal/model"
	"aio-scheduler/service"
	"github.com/gin-gonic/gin"
)

func DeployPython(context *gin.Context) {
	deployInfo := model.DeployInfo{}
	err := context.ShouldBind(&deployInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	go service.DeployPython(deployInfo)
	context.JSON(200, response.Success())
}
