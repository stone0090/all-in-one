package global

import (
	"github.com/Unknwon/goconfig"
	"os"
)

var Enver *Env

type Env struct {
	Mode              string // 运行模式，dev：开发模式，prod：生产模式
	SchedulerLogPath  string // 日志路径，/home/admin/logs
	SchedulerWorkPath string // 工作路径，/home/admin/aio-scheduler
	SchedulerHost     string // 调度服务地址，http://0.0.0.0
	SchedulerPort     string // 调度服务端口，http://6000
	FaasWorkPath      string // faas路径，/home/admin/aio-faas/python-basic
	FaasHost          string // faas服务地址，http://0.0.0.0
	FaasPort          string // faas服务端口，6001
}

func GetFromOsOrConf(config *goconfig.ConfigFile, section string, key string) string {
	envName := section + "_" + key
	if os.Getenv(envName) != "" {
		return os.Getenv(envName)
	}
	return config.MustValue(section, key)
}
