package model

type RunContext struct {
	RequestId      string
	Status         string
	StartTime      int64
	EndTime        int64
	InputParamMap  map[string]interface{}      // key: portId.paramCode, value: data
	OutputParamMap map[string]interface{}      // key: portId.paramCode, value: data
	NodeContextMap map[string]NodeContext      // key: nodeId, value: NodeContext
	ReceiveChanMap map[string]chan interface{} // 接受前置节点数据，key: portId, value: chan
	SendChanMap    map[string]chan interface{} // 向后置节点传数据，key: portId, value: chan
	MasterChan     chan NodeContext            // 用于向主协程传递数据
}

type NodeContext struct {
	NodeId         string
	Status         string
	StartTime      int64
	EndTime        int64
	InputParamMap  map[string]interface{} // key: paramCode, value: data
	OutputParamMap map[string]interface{} // key: paramCode, value: data
}
