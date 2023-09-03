package request

type FaasConfig struct {
	ServiceId   string `json:"service_id"`
	ServicePort int    `json:"service_port"`
	AlgoCode    string `json:"algo_code"`
}
