package global

import "aio-scheduler/common/request"

var runInfo RunInfo

type RunInfo struct {
	RequestId             string
	DeployInfo            request.DeployInfo
	NodeId2ServicePortMap map[string]int          // key: nodeId, value: servicePort
	Out2InPortIdMap       map[string]string       // key: OutPortId, value: InPortId
	In2OutPortIdMap       map[string]string       // key: InPortId, value: OutPortId
	NodeId2NodeMap        map[string]request.Node // key: nodeId, value: model.Node
	PortId2NodeMap        map[string]request.Node // key: portId, value: model.Node
	PortId2PortMap        map[string]request.Port // key: portId, value: model.Port
}

func NewRunInfo(deployInfo request.DeployInfo, nodeId2ServicePortMap map[string]int) RunInfo {
	runInfo = RunInfo{}
	runInfo.RequestId = deployInfo.RequestId
	runInfo.DeployInfo = deployInfo
	runInfo.NodeId2ServicePortMap = nodeId2ServicePortMap
	runInfo.Out2InPortIdMap = make(map[string]string)
	runInfo.In2OutPortIdMap = make(map[string]string)
	runInfo.NodeId2NodeMap = make(map[string]request.Node)
	runInfo.PortId2NodeMap = make(map[string]request.Node)
	runInfo.PortId2PortMap = make(map[string]request.Port)
	for _, edge := range deployInfo.Edges {
		fromPortId := edge.SourcePortId
		toPortId := edge.TargetPortId
		runInfo.Out2InPortIdMap[toPortId] = fromPortId
		runInfo.In2OutPortIdMap[fromPortId] = toPortId
	}
	for _, node := range deployInfo.Nodes {
		runInfo.NodeId2NodeMap[node.Id] = node
		for _, port := range node.Ports {
			runInfo.PortId2PortMap[port.Id] = port
			runInfo.PortId2NodeMap[port.Id] = node
		}
	}
	return runInfo
}
