package response

import "aio-scheduler/common/constants"

type RestResult struct {
	Success bool        `json:"success"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}

func Success(data interface{}) *RestResult {
	return &RestResult{
		Success: true,
		Message: constants.Success,
		Data:    data,
	}
}

func Failure(message string) *RestResult {
	return &RestResult{
		Success: false,
		Message: message,
		Data:    nil,
	}
}

func FailureWithData(message string, date interface{}) *RestResult {
	return &RestResult{
		Success: false,
		Message: message,
		Data:    date,
	}
}
