import type {NsJsonSchemaForm} from '@antv/xflow'
import {controlMapService, ControlShapeEnum} from './form-controls'
import {MODELS} from '@antv/xflow'

export function delay(ms: number) {
  return new Promise(resolve => setTimeout(() => resolve(true), ms))
}

let first = true;

export const formSchemaService: NsJsonSchemaForm.IFormSchemaService = async args => {
  if (first) {
    await delay(200)
    first = false
  }
  const {targetData, modelService, targetType} = args
  const graphMeta = await MODELS.GRAPH_META.useValue(modelService)
  const {flowId, experiment = {}, opGroups = []} = graphMeta
  console.log('formSchemaService', targetType, targetData, graphMeta, args, flowId, experiment, opGroups)

  if (targetType === 'canvas') {
    return {
      tabs: [
        {
          name: '画布配置',
          groups: [
            {
              name: 'groupName',
              controls: [
                {
                  name: 'exName',
                  label: '画布名称',
                  shape: 'Input',
                  disabled: true,
                  required: true,
                  // tooltip: '图的业务项目名',
                  // extra: '和图的ID对应',
                  placeholder: 'please write something',
                  value: experiment?.exName,
                  defaultValue: '画布名称', // 可以认为是默认值
                  hidden: false,
                  // options: [{ title: '', value: '' }],
                  // originData: {}, // 原始数据
                },
                {
                  label: '部署类型',
                  name: 'publishType',
                  /** 使用自定义shape */
                  shape: ControlShapeEnum.PUBLISHTYPE,
                  disabled: false,
                  required: true,
                  // tooltip: 'JSON 数据',
                  // placeholder: 'please write something',
                  value: experiment?.publishType,
                  defaultValue: '', // 可以认为是默认值
                  hidden: false,
                  // options: [{ title: '', value: '' }],
                  // originData: {}, // 原始数据
                },
              ],
            },
          ],
        },
      ],
    }
  }

  const nodeSchema: NsJsonSchemaForm.ISchema = {
    tabs: [
      {
        name: '节点配置',
        groups: [
          {
            name: 'groupName',
            controls: [
              {
                name: 'nodeName',
                label: '节点名称',
                shape: 'Input',
                disabled: true,
                required: true,
                // tooltip: '图的业务项目名',
                // extra: '和图的ID对应',
                placeholder: '请输入节点名称',
                value: targetData?.label,
                defaultValue: '节点名称', // 可以认为是默认值
                hidden: false,
                // options: [{ title: '', value: '' }],
                // originData: {}, // 原始数据
              },
            ],
          },
        ],
      },
    ],
  }

  return nodeSchema
}

export const formValueUpdateService: NsJsonSchemaForm.IFormValueUpdateService = async args => {
  console.log('formValueUpdateService', args)
}

export {controlMapService}
