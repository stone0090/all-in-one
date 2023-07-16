package client

import (
	"aio-scheduler/common/utils"
	"aio-scheduler/service/global"
)

func HealthCheckPython() (string, error) {
	result, err := utils.HttpGet(global.Enver.FaasUrl + "/aio/faas/health/check")
	if err != nil {
		return "", err
	}
	return string(result.([]byte)), nil
}

func InvokePython(params []byte) (interface{}, error) {
	result, err := utils.HttpPost(global.Enver.FaasUrl+"/aio/faas/invoke", params)
	if err != nil {
		return nil, err
	}
	data, err := utils.GetDateFromRestResult(result)
	if err != nil {
		return nil, err
	}
	return data, nil
}
