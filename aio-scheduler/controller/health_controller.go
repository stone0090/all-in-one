package controller

import (
	"aio-scheduler/common/constants"
	"github.com/gin-gonic/gin"
)

func Check(context *gin.Context) {
	context.String(200, constants.Success)
}
