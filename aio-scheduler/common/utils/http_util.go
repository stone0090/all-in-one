package utils

import (
	"aio-scheduler/common/response"
	"bytes"
	"encoding/json"
	"errors"
	"github.com/labstack/gommon/log"
	"io"
	"io/ioutil"
	"net/http"
)

func HttpGet(url string) (interface{}, error) {
	client := http.Client{}
	resp, err := client.Get(addHttpPrefix(url))
	if err != nil {
		log.Error("client.Get(url)失败！" + err.Error())
		return "", err
	}
	defer func(Body io.ReadCloser) {
		err := Body.Close()
		if err != nil {
			log.Error("关闭http请求失败！" + err.Error())
		}
	}(resp.Body)
	result, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}
	return result, nil

}

func HttpPost(url string, params []byte) (interface{}, error) {
	req, err := http.NewRequest("POST", addHttpPrefix(url), bytes.NewBuffer(params))
	if err != nil {
		log.Error("http.NewRequest(\"POST\", url, bytes.NewBuffer(body))失败！" + err.Error())
		return "", err
	}
	req.Header.Set("Content-Type", "application/json;charset=UTF-8")
	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		log.Error("client.Do(req)失败！" + err.Error())
		return "", err
	}
	defer func(Body io.ReadCloser) {
		err := Body.Close()
		if err != nil {
			log.Error("关闭http请求失败！" + err.Error())
		}
	}(resp.Body)
	result, err := ioutil.ReadAll(resp.Body)
	return result, nil
}

func GetDateFromRestResult(result interface{}) (interface{}, error) {
	restResult := response.RestResult{}
	err := json.Unmarshal(result.([]byte), &restResult)
	if err != nil {
		return nil, err
	}
	if restResult.Success == false {
		return nil, errors.New(restResult.Message)
	}
	return restResult.Data, nil
}

func addHttpPrefix(url string) string {
	if url[:7] != "http://" && url[:8] != "https://" {
		url = "http://" + url
	}
	return url
}
