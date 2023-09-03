package request

type DeployInfo struct {
	RequestId    string       `json:"requestId"`
	Nodes        []Node       `json:"nodes"`
	Edges        []Edge       `json:"edges"`
	ScheduleInfo ScheduleInfo `json:"scheduleInfo"`
}

type Node struct {
	Id                  string `json:"id"`
	Label               string `json:"label"`
	Ports               []Port `json:"ports"`
	OpCode              string `json:"opCode"`
	OpName              string `json:"opName"`
	ProgrammingLanguage string `json:"programmingLanguage"`
	AlgorithmCode       string `json:"algorithmCode"`
	AlgorithmPath       string `json:"algorithmPath"`
}

type Port struct {
	Id                  string      `json:"id"`
	Tooltip             string      `json:"tooltip"`
	Type                string      `json:"type"` // input/output
	OpParamCode         string      `json:"opParamCode"`
	OpParamName         string      `json:"opParamName"`
	OpParamType         string      `json:"opParamType"` // int/double/boolean/string
	OpParamRequired     bool        `json:"opParamRequired"`
	OpParamDefaultValue interface{} `json:"opParamDefaultValue"`
}
type Edge struct {
	Id           string `json:"id"`
	Source       string `json:"source"`
	Target       string `json:"target"`
	SourcePortId string `json:"sourcePortId"`
	TargetPortId string `json:"targetPortId"`
}

type ScheduleInfo struct {
	Type       string `json:"type"`
	Expression string `json:"expression"`
}
