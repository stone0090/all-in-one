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
	workDir := os.Getenv("AIO_SCHEDULER_WD")
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
		Mode:         *mode,
		LogPath:      global.GetFromOsOrConf(config, "self", "logPath"),
		SchedulerUrl: global.GetFromOsOrConf(config, "self", "schedulerUrl"),
		FaasPath:     global.GetFromOsOrConf(config, "faas", "faasPath"),
		FaasUrl:      global.GetFromOsOrConf(config, "faas", "faasUrl"),
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
		group.POST("/python/deploy", controller.PythonDeploy)
		group.POST("/python/invoke", controller.PythonInvoke)
		group.GET("/python/health/check", controller.PythonHealthCheck)
	}
	//engine.POST("/command/exec", controller.ExecCommand)
	// TODO：接口切面打印日志
	err := engine.Run(global.Enver.SchedulerUrl)
	if err != nil {
		log.Fatal(err.Error())
	}
}
