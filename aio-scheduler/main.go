package main

import (
	"aio-scheduler/common/constants"
	"aio-scheduler/controller"
	"aio-scheduler/service/global"
	"flag"
	"fmt"
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
	workPath := os.Getenv("AIO_SCHEDULER_WORK_PATH")
	if workPath == "" {
		workPath, err = os.Getwd()
		if err != nil {
			panic("获取工作目录失败！" + err.Error())
		}
	}
	mode := flag.String(constants.Mode, constants.Dev, "运行模式")
	flag.Parse()
	var confPath string
	if constants.Prod == *mode {
		confPath = workPath + "/conf/prod.ini"
	} else {
		confPath = workPath + "/conf/dev.ini"
	}
	config, err := goconfig.LoadConfigFile(confPath)
	if err != nil {
		panic("加载配置文件失败！" + err.Error())
	}
	global.Enver = &global.Env{
		Mode:              *mode,
		SchedulerWorkPath: workPath,
		SchedulerLogPath:  global.GetFromOsOrConf(config, "AIO_SCHEDULER", "LOG_PATH"),
		SchedulerHost:     global.GetFromOsOrConf(config, "AIO_SCHEDULER", "HOST"),
		SchedulerPort:     global.GetFromOsOrConf(config, "AIO_SCHEDULER", "PORT"),
		FaasWorkPath:      global.GetFromOsOrConf(config, "AIO_FAAS", "WORK_PATH"),
		FaasHost:          global.GetFromOsOrConf(config, "AIO_FAAS", "HOST"),
		FaasPort:          global.GetFromOsOrConf(config, "AIO_FAAS", "PORT"),
	}
	fmt.Println("配置文件加载成功，运行模式为[" + global.Enver.Mode + "]")
}

func initLog() {
	err := os.MkdirAll(global.Enver.SchedulerLogPath, 0777)
	if err != nil {
		panic("创建日志目录失败！" + err.Error())
	}
	file, err := os.OpenFile(global.Enver.SchedulerLogPath+"/aio-scheduler.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
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
