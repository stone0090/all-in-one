package service

import (
	"aio-scheduler/dal/model"
	"aio-scheduler/service/global"
	"github.com/labstack/gommon/log"
	"os"
	"os/exec"
)

func DeployPython(deployInfo model.DeployInfo) {
	log.Info("service.DeployPython Start")
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
	go func() {
		cmd := exec.Command("/bin/bash", global.Enver.FaasPath+"/start.sh")
		output, err := cmd.Output()
		if err != nil {
			log.Error("执行start.sh失败！" + err.Error())
			return
		}
		log.Info("执行start.sh成功！" + string(output))
	}()
	log.Info("service.DeployPython End")
}
