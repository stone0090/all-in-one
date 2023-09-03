package controller

import (
	"aio-scheduler/common/request"
	"aio-scheduler/common/response"
	"github.com/gin-gonic/gin"
	"os/exec"
)

func ExecCommand(context *gin.Context) {
	commandInfo := request.CommandInfo{}
	err := context.ShouldBind(&commandInfo)
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	cmd := exec.Command(commandInfo.Name, commandInfo.Args...)
	output, err := cmd.Output()
	if err != nil {
		context.JSON(200, response.Failure(err.Error()))
		return
	}
	context.JSON(200, response.Success(output))
}
