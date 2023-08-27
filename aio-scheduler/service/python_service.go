package service

import (
	"aio-scheduler/dal/model"
	"aio-scheduler/service/global"
	"encoding/json"
	"github.com/labstack/gommon/log"
	"os"
	"os/exec"
)

func DeployPython(node model.Node, port int) {
	file, err := os.OpenFile(global.Enver.FaasPath+"/config.json", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		log.Error("创建config.json失败！" + err.Error())
		return
	}
	defer func(file *os.File) {
		err := file.Close()
		if err != nil {
			log.Error("关闭config.json失败！" + err.Error())
			return
		}
	}(file)
	err = file.Truncate(0)
	if err != nil {
		log.Error("清空config.json失败！" + err.Error())
		return
	}
	config := model.FaasConfig{
		ServiceId:   node.NodeId,
		ServicePort: port,
		AlgoCode:    node.AlgoCode,
	}
	encoder := json.NewEncoder(file)
	err = encoder.Encode(config)
	if err != nil {
		log.Error("写入config.json失败！" + err.Error())
		return
	}
	log.Infof("写入config.json成功！%v", config)
	cmd := exec.Command("/bin/bash", global.Enver.FaasPath+"/start.sh")
	output, err := cmd.Output()
	if err != nil {
		log.Error("执行start.sh失败！" + err.Error())
		return
	}
	log.Info("执行start.sh成功！" + string(output))
}
