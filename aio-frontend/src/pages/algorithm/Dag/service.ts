/* eslint-disable @typescript-eslint/no-unused-vars */
import {DND_RENDER_ID, NODE_WIDTH, NODE_HEIGHT} from './constant'
import {uuidv4, NsGraph, NsGraphStatusCommand} from '@antv/xflow'
import type {NsRenameNodeCmd} from './cmd-extensions/cmd-rename-node-modal'
import type {NsNodeCmd, NsEdgeCmd, NsGraphCmd} from '@antv/xflow'
import type {NsDeployDagCmd} from './cmd-extensions/cmd-deploy'
import {requestGet, requestPost} from "@/services/api";
import {message} from "antd";

/** mock 后端接口调用 */
export namespace MockApi {
  export const NODE_COMMON_PROPS = {
    renderKey: DND_RENDER_ID,
    width: NODE_WIDTH,
    height: NODE_HEIGHT,
  } as const

  /** 查图的meta元信息 */
  export const queryGraphMeta: NsGraphCmd.GraphMeta.IArgs['graphMetaService'] = async args => {
    console.log('queryGraphMeta', args)
    let experiment = {}, opGroups = [];
    const experimentResult: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/experiment/get', {"id": args.meta.flowId});
    if (!experimentResult?.success) {
      message.error('获取画布详情失败！');
    } else {
      experiment = experimentResult.data;
    }
    const opGroupsResult: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/dag/listOpGroups');
    if (!opGroupsResult?.success) {
      message.error('获取算法组件失败！');
    } else {
      opGroups = opGroupsResult.data;
    }
    await Promise.all([experimentResult, opGroupsResult]);
    return {...args, flowId: args.meta.flowId, experiment, opGroups}
  }
  /** 加载图数据的api */
  export const loadGraphData = async (graphMeta: NsGraph.IGraphMeta) => {
    console.log('loadGraphData', graphMeta)
    const {nodeDag = {}} = graphMeta?.experiment || {}
    const nodeDagJson = JSON.parse(nodeDag)
    const nodes: NsGraph.INodeConfig[] = nodeDagJson["nodes"]
    const edges: NsGraph.IEdgeConfig[] = nodeDagJson["edges"]
    return {nodes, edges}
  }
  /** 保存图数据的api */
  export const saveGraphData: NsGraphCmd.SaveGraphData.IArgs['saveGraphDataService'] = async (
    graphMeta: NsGraph.IGraphMeta,
    graphData: NsGraph.IGraphData,
  ) => {
    console.log('saveGraphData', graphMeta, graphData)
    const record = {
      "id": graphMeta.flowId,
      "nodeDag": JSON.stringify(graphData),
    };
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/experiment/editDetail', record);
    if (result?.success) {
      message.success('保存成功！');
    }
    return {
      success: true,
      data: graphData,
    }
  }
  /** 部署图数据的api */
  export const deployDagService: NsDeployDagCmd.IDeployDagService = async (
    meta: NsGraph.IGraphMeta,
    graphData: NsGraph.IGraphData,
  ) => {
    console.log('deployService api', meta, graphData)
    return {
      success: true,
      data: graphData,
    }
  }

  /** 添加节点api */
  export const addNode: NsNodeCmd.AddNode.IArgs['createNodeService'] = async (
    args: NsNodeCmd.AddNode.IArgs,
  ) => {
    console.info('addNode service running, add node:', args)
    const {operator} = args.nodeConfig
    const inputParamJson = JSON.parse(operator.inputParam);
    const outputParamJson = JSON.parse(operator.outputParam);
    let portItems = [];
    for (const key in inputParamJson) {
      portItems.push({
        type: NsGraph.AnchorType.INPUT,
        group: NsGraph.AnchorGroup.TOP,
        tooltip: inputParamJson[key].name + " (" + inputParamJson[key].type + ")",
      })
    }
    for (const key in outputParamJson) {
      portItems.push({
        type: NsGraph.AnchorType.OUTPUT,
        group: NsGraph.AnchorGroup.BOTTOM,
        tooltip: outputParamJson[key].name + " (" + outputParamJson[key].type + ")",
      })
    }
    const {id, ports = portItems, groupChildren} = args.nodeConfig
    const nodeId = id || uuidv4()
    /** 这里添加连线桩 */
    const node: NsNodeCmd.AddNode.IArgs['nodeConfig'] = {
      ...NODE_COMMON_PROPS,
      ...args.nodeConfig,
      id: nodeId,
      ports: (ports as NsGraph.INodeAnchor[]).map(port => {
        return {...port, id: uuidv4()}
      }),
    }
    /** group没有链接桩 */
    if (groupChildren && groupChildren.length) {
      node.ports = []
    }
    return node
  }

  /** 更新节点 name，可能依赖接口判断是否重名，返回空字符串时，不更新 */
  export const renameNode: NsRenameNodeCmd.IUpdateNodeNameService = async (
    name,
    node,
    graphMeta,
  ) => {
    console.log('rename node', node, name, graphMeta)
    return {err: null, nodeName: name}
  }

  /** 删除节点的api */
  export const delNode: NsNodeCmd.DelNode.IArgs['deleteNodeService'] = async args => {
    console.info('delNode service running, del node:', args.nodeConfig.id)
    return true
  }

  /** 添加边的api */
  export const addEdge: NsEdgeCmd.AddEdge.IArgs['createEdgeService'] = async args => {
    console.info('addEdge service running, add edge:', args)
    const {edgeConfig} = args
    return {
      ...edgeConfig,
      id: uuidv4(),
    }
  }

  /** 删除边的api */
  export const delEdge: NsEdgeCmd.DelEdge.IArgs['deleteEdgeService'] = async args => {
    console.info('delEdge service running, del edge:', args)
    return true
  }

  let runningNodeId = 0
  const statusMap = {} as NsGraphStatusCommand.IStatusInfo['statusMap']
  let graphStatus: NsGraphStatusCommand.StatusEnum = NsGraphStatusCommand.StatusEnum.DEFAULT
  export const graphStatusService: NsGraphStatusCommand.IArgs['graphStatusService'] = async () => {
    if (runningNodeId < 4) {
      statusMap[`node${runningNodeId}`] = {status: NsGraphStatusCommand.StatusEnum.SUCCESS}
      statusMap[`node${runningNodeId + 1}`] = {status: NsGraphStatusCommand.StatusEnum.PROCESSING}
      runningNodeId += 1
      graphStatus = NsGraphStatusCommand.StatusEnum.PROCESSING
    } else {
      runningNodeId = 0
      statusMap.node4 = {status: NsGraphStatusCommand.StatusEnum.SUCCESS}
      graphStatus = NsGraphStatusCommand.StatusEnum.SUCCESS
    }
    return {
      graphStatus: graphStatus,
      statusMap: statusMap,
    }
  }
  export const stopGraphStatusService: NsGraphStatusCommand.IArgs['graphStatusService'] =
    async () => {
      Object.entries(statusMap).forEach(([, val]) => {
        const {status} = val as { status: NsGraphStatusCommand.StatusEnum }
        if (status === NsGraphStatusCommand.StatusEnum.PROCESSING) {
          val.status = NsGraphStatusCommand.StatusEnum.ERROR
        }
      })
      return {
        graphStatus: NsGraphStatusCommand.StatusEnum.ERROR,
        statusMap: statusMap,
      }
    }
}