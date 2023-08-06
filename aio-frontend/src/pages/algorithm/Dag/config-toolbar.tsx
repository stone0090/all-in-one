import type { IToolbarItemOptions } from '@antv/xflow'
import { createToolbarConfig, uuidv4 } from '@antv/xflow'
import type { IModelService } from '@antv/xflow'
import {
  XFlowGraphCommands,
  XFlowGroupCommands,
  XFlowDagCommands,
  NsGraphStatusCommand,
  MODELS,
  IconStore,
} from '@antv/xflow'
import {
  UngroupOutlined,
  SaveOutlined,
  CloudSyncOutlined,
  GroupOutlined,
  GatewayOutlined,
  PlaySquareOutlined,
  StopOutlined,
} from '@ant-design/icons'
import { MockApi } from './service'
import { CustomCommands } from './cmd-extensions/constants'
import type { NsDeployDagCmd } from './cmd-extensions/cmd-deploy'
import type { NsGraphCmd, NsGroupCmd } from '@antv/xflow'
import { GROUP_NODE_RENDER_ID } from './constant'
import { Popconfirm } from 'antd'
import React from 'react'

export namespace NSToolbarConfig {
  /** 注册icon 类型 */
  IconStore.set('SaveOutlined', SaveOutlined)
  IconStore.set('CloudSyncOutlined', CloudSyncOutlined)
  IconStore.set('GatewayOutlined', GatewayOutlined)
  IconStore.set('GroupOutlined', GroupOutlined)
  IconStore.set('UngroupOutlined', UngroupOutlined)
  IconStore.set('PlaySquareOutlined', PlaySquareOutlined)
  IconStore.set('StopOutlined', StopOutlined)

  /** toolbar依赖的状态 */
  export interface IToolbarState {
    isMultiSelectionActive: boolean
    isNodeSelected: boolean
    isGroupSelected: boolean
    isProcessing: boolean
  }

  export const getDependencies = async (modelService: IModelService) => {
    return [
      await MODELS.SELECTED_CELLS.getModel(modelService),
      await MODELS.GRAPH_ENABLE_MULTI_SELECT.getModel(modelService),
      await NsGraphStatusCommand.MODEL.getModel(modelService),
    ]
  }

  /** toolbar依赖的状态 */
  export const getToolbarState = async (modelService: IModelService) => {
    // isMultiSelectionActive
    const { isEnable: isMultiSelectionActive } = await MODELS.GRAPH_ENABLE_MULTI_SELECT.useValue(
      modelService,
    )
    // isGroupSelected
    const isGroupSelected = await MODELS.IS_GROUP_SELECTED.useValue(modelService)
    // isNormalNodesSelected: node不能是GroupNode
    const isNormalNodesSelected = await MODELS.IS_NORMAL_NODES_SELECTED.useValue(modelService)
    // statusInfo
    const statusInfo = await NsGraphStatusCommand.MODEL.useValue(modelService)

    return {
      isNodeSelected: isNormalNodesSelected,
      isGroupSelected,
      isMultiSelectionActive,
      isProcessing: statusInfo.graphStatus === NsGraphStatusCommand.StatusEnum.PROCESSING,
    } as NSToolbarConfig.IToolbarState
  }

  export const getToolbarItems = async (state: IToolbarState) => {
    const toolbarGroup1: IToolbarItemOptions[] = []
    const toolbarGroup2: IToolbarItemOptions[] = []

    /** 保存数据 */
    toolbarGroup1.push({
      id: XFlowGraphCommands.SAVE_GRAPH_DATA.id,
      iconName: 'SaveOutlined',
      tooltip: '保存数据',
      onClick: async ({ commandService }) => {
        commandService.executeCommand<NsGraphCmd.SaveGraphData.IArgs>(
          XFlowGraphCommands.SAVE_GRAPH_DATA.id,
          { saveGraphDataService: (meta, graphData) => MockApi.saveGraphData(meta, graphData) },
        )
      },
    })

    /** 开启框选 */
    toolbarGroup1.push({
      id: XFlowGraphCommands.GRAPH_TOGGLE_MULTI_SELECT.id,
      tooltip: '开启框选',
      iconName: 'GatewayOutlined',
      active: state.isMultiSelectionActive,
      onClick: async ({ commandService }) => {
        commandService.executeCommand<NsGraphCmd.GraphToggleMultiSelect.IArgs>(
          XFlowGraphCommands.GRAPH_TOGGLE_MULTI_SELECT.id,
          {},
        )
      },
    })

    /** 部署服务按钮 */
    toolbarGroup2.push({
      iconName: 'CloudSyncOutlined',
      tooltip: '部署',
      id: CustomCommands.DEPLOY_SERVICE.id,
      onClick: ({ commandService }) => {
        commandService.executeCommand<NsDeployDagCmd.IArgs>(CustomCommands.DEPLOY_SERVICE.id, {
          deployDagService: (meta, graphData) => MockApi.deployDagService(meta, graphData),
        })
      },
    })

    /** 强制停止按钮 */
    toolbarGroup2.push({
      id: XFlowDagCommands.QUERY_GRAPH_STATUS.id + 'stop',
      tooltip: '强制停止',
      iconName: 'StopOutlined',
      isEnabled: state.isProcessing,
      onClick: async ({ commandService }) => {
        commandService.executeCommand<NsGraphStatusCommand.IArgs>(
          XFlowDagCommands.QUERY_GRAPH_STATUS.id,
          {
            graphStatusService: MockApi.stopGraphStatusService,
            loopInterval: 5000,
          },
        )
      },
      render: props => {
        return (
          <Popconfirm
            title="确定停止执行？"
            onConfirm={() => {
              props.onClick()
            }}
          >
            {props.children}
          </Popconfirm>
        )
      },
    })

    /** 开启框选 */
    toolbarGroup2.push({
      id: XFlowDagCommands.QUERY_GRAPH_STATUS.id + 'play',
      tooltip: 'API调用',
      iconName: 'PlaySquareOutlined',
      isEnabled: state.isProcessing,
      onClick: async ({ commandService }) => {
        commandService.executeCommand<NsGraphStatusCommand.IArgs>(
          XFlowDagCommands.QUERY_GRAPH_STATUS.id,
          {
            graphStatusService: MockApi.graphStatusService,
            loopInterval: 3000,
          },
        )
      },
    })

    return [
      { name: 'graphData', items: toolbarGroup1 },
      { name: 'groupOperations', items: toolbarGroup2 },
    ]
  }
}
export const useToolbarConfig = createToolbarConfig(toolbarConfig => {
  /** 生产 toolbar item */
  toolbarConfig.setToolbarModelService(async (toolbarModel, modelService, toDispose) => {
    const updateToolbarModel = async () => {
      const state = await NSToolbarConfig.getToolbarState(modelService)
      const toolbarItems = await NSToolbarConfig.getToolbarItems(state)
      toolbarModel.setValue(toolbar => {
        toolbar.mainGroups = toolbarItems
      })
    }
    const models = await NSToolbarConfig.getDependencies(modelService)
    const subscriptions = models.map(model => {
      return model.watch(async () => {
        updateToolbarModel()
      })
    })
    toDispose.pushAll(subscriptions)
  })
})
