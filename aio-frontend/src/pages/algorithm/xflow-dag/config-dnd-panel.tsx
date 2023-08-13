/* eslint-disable @typescript-eslint/no-unused-vars */
import {uuidv4} from '@antv/xflow'
import {XFlowNodeCommands} from '@antv/xflow'
import {DND_RENDER_ID} from './constant'
import type {NsNodeCmd} from '@antv/xflow'
import type {NsNodeCollapsePanel} from '@antv/xflow'
import {Card} from 'antd'
import ReactJson from 'react-json-view'
import {delay} from "@/pages/algorithm/xflow-dag/config-form";

export const onNodeDrop: NsNodeCollapsePanel.IOnNodeDrop = async (node, commands, modelService) => {
  const args: NsNodeCmd.AddNode.IArgs = {
    nodeConfig: {...node, id: uuidv4()},
  }
  commands.executeCommand(XFlowNodeCommands.ADD_NODE.id, args)
}

const NodeDescription = (props: { operator: any }) => {
  return (
    <Card size="small" title={props.operator.opName} style={{width: '250px'}} bordered={false}>
      算子入参：
      <ReactJson name={null} src={JSON.parse(props.operator.inputParam)} displayDataTypes={false}/>
      <br/>
      算子出参：
      <ReactJson name={null} src={JSON.parse(props.operator.outputParam)} displayDataTypes={false}/>
    </Card>
  )
}

let first = true;

export const nodeDataService: NsNodeCollapsePanel.INodeDataService = async (graphMeta, modelService) => {
  if (first) {
    await delay(200)
    first = false
  }
  const {flowId, dag = {}, opGroups = []} = graphMeta
  console.log("nodeDataService", graphMeta, modelService, flowId, dag, opGroups)

  return opGroups.map((item: any) => {
    return {
      id: item.id,
      header: item.groupName,
      children: item.operatorList.map((operator: any) => {
        return {
          id: operator.id,
          label: operator.opName,
          parentId: item.id,
          renderKey: DND_RENDER_ID,
          operator: operator,
          popoverContent: <NodeDescription operator={operator}/>,
        }
      }),
    }
  });
}

export const searchService: NsNodeCollapsePanel.ISearchService = async (
  nodes: NsNodeCollapsePanel.IPanelNode[] = [],
  keyword: string,
) => {
  const list = nodes.filter(node => node.label.includes(keyword))
  console.log(list, keyword, nodes)
  return list
}
