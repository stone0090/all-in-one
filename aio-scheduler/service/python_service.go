package service

import (
	"aio-scheduler/dal/model"
	"aio-scheduler/service/global"
	"github.com/labstack/gommon/log"
	"os"
	"os/exec"
)

func DeployPython(deployInfo model.DeployInfo) {
	file, err := os.OpenFile(global.Enver.FaasPath+"/inject_code.py", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		log.Error("创建inject_code.py失败！" + err.Error())
		return
	}
	defer func(file *os.File) {
		err := file.Close()
		if err != nil {
			log.Error("关闭inject_code.py失败！" + err.Error())
			return
		}
	}(file)
	err = file.Truncate(0)
	if err != nil {
		log.Error("清空inject_code.py失败！" + err.Error())
		return
	}
	_, err = file.WriteString(deployInfo.AlgoCode)
	if err != nil {
		log.Error("写入inject_code.py失败！" + err.Error())
		return
	}
	cmd := exec.Command("sh", global.Enver.FaasPath+"/start.sh")
	output, err := cmd.Output()
	if err != nil {
		log.Error("执行start.sh失败！" + err.Error())
		return
	}
	log.Info("执行start.sh成功！" + string(output))
	// todo: 健康检查，调用aio-backend接口，通知部署成功
}
