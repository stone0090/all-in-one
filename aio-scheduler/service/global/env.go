package global

import (
	"github.com/Unknwon/goconfig"
	"os"
)

var Enver *Env

type Env struct {
	Mode         string // 运行模式，dev：开发模式，prod：生产模式
	LogPath      string // 日志路径，/home/admin/logs
	WorkPath     string // 工作路径，/home/admin/aio-scheduler
	SchedulerUrl string // 调度服务地址，http://0.0.0.0:6000
	FaasPath     string // faas路径，/home/admin/aio-faas/python-basic
	FaasUrl      string // faas服务地址，http://0.0.0.0:6001
	BizId        string // 后端业务id，OPERATOR-000001
	BackendUrl   string // 后端服务地址，http://0.0.0.0:7001
}

func GetFromOsOrConf(config *goconfig.ConfigFile, section string, key string) string {
	envName := section + "_" + key
	if os.Getenv(envName) != "" {
		return os.Getenv(envName)
	}
	return config.MustValue(section, key)
}
