package client

import (
	"aio-scheduler/common/utils"
	"aio-scheduler/service/global"
)

func HealthCheck(port string) (string, error) {
	url := global.Enver.FaasHost + ":" + port
	result, err := utils.HttpGet(url + "/aio/faas/health/check")
	if err != nil {
		return "", err
	}
	return string(result.([]byte)), nil
}

func Invoke(port string, params []byte) (interface{}, error) {
	url := global.Enver.FaasHost + ":" + port
	result, err := utils.HttpPost(url+"/aio/faas/invoke", params)
	if err != nil {
		return nil, err
	}
	data, err := utils.GetDateFromRestResult(result)
	if err != nil {
		return nil, err
	}
	return data, nil
}
