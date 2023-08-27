package main

import (
	"aio-scheduler/common/constants"
	"aio-scheduler/controller"
	"aio-scheduler/service/global"
	"flag"
	"github.com/Unknwon/goconfig"
	"github.com/gin-gonic/gin"
	"github.com/labstack/gommon/log"
	"io"
	"os"
)

func main() {
	initEnv()
	initLog()
	initWeb()
}

func initEnv() {
	var err error
	workDir := os.Getenv("AIO_SCHEDULER_WORK_DIR")
	if workDir == "" {
		workDir, err = os.Getwd()
		if err != nil {
			panic("获取工作目录失败！" + err.Error())
		}
	}
	mode := flag.String(constants.Mode, constants.Dev, "运行模式")
	flag.Parse()
	var confPath string
	if constants.Prod == *mode {
		confPath = workDir + "/conf/prod.ini"
	} else {
		confPath = workDir + "/conf/dev.ini"
	}
	config, err := goconfig.LoadConfigFile(confPath)
	if err != nil {
		panic("加载配置文件失败！" + err.Error())
	}
	global.Enver = &global.Env{
		Mode:          *mode,
		LogPath:       global.GetFromOsOrConf(config, "self", "logPath"),
		SchedulerHost: global.GetFromOsOrConf(config, "self", "schedulerHost"),
		SchedulerPort: global.GetFromOsOrConf(config, "self", "schedulerPort"),
		FaasPath:      global.GetFromOsOrConf(config, "faas", "faasPath"),
		FaasHost:      global.GetFromOsOrConf(config, "faas", "faasHost"),
		FaasPort:      global.GetFromOsOrConf(config, "faas", "faasPort"),
	}
}

func initLog() {
	err := os.MkdirAll(global.Enver.LogPath, 0777)
	if err != nil {
		panic("创建日志目录失败！" + err.Error())
	}
	file, err := os.OpenFile(global.Enver.LogPath+"/aio-scheduler.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		panic("创建日志文件失败！" + err.Error())
	}
	log.SetOutput(io.MultiWriter(os.Stdout, file))
	log.Info("日志初始化成功！")
}

func initWeb() {
	engine := gin.Default()
	group := engine.Group("/aio/scheduler")
	{
		group.GET("/health/check", controller.Check)
		group.POST("/faas/deploy", controller.Deploy)
		group.POST("/faas/invoke", controller.Invoke)
		group.GET("/faas/health/check", controller.HealthCheck)
	}
	//engine.POST("/command/exec", controller.ExecCommand)
	err := engine.Run(global.Enver.SchedulerHost + ":" + global.Enver.SchedulerPort)
	if err != nil {
		log.Fatal(err.Error())
	}
}
