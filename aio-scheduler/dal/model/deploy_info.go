package model

type DeployInfo struct {
	RequestId string `json:"requestId"`
	Nodes     []Node `json:"nodes"`
}

type Node struct {
	NodeId     string   `json:"nodeId"`
	Previous   []string `json:"previous"`
	Next       []string `json:"next"`
	NeedDeploy bool     `json:"needDeploy"`
	Language   string   `json:"language"`
	AlgoCode   string   `json:"algoCode"`
}
