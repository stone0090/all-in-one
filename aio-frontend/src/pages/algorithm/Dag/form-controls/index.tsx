import type { NsJsonSchemaForm } from '@antv/xflow'
import { EditorShape } from './custom-editor'
import { LinkShape } from './link'
import {DDLShape, PublishTypeDDLShape, PublishTypeShape} from "@/pages/algorithm/Dag/form-controls/publish-type";

/** 自定义form控件 */
export enum ControlShapeEnum {
  'EDITOR' = 'EDITOR',
  'PUBLISHTYPE' = 'PUBLISHTYPE',
}

export const controlMapService: NsJsonSchemaForm.IControlMapService = controlMap => {
  controlMap.set(ControlShapeEnum.EDITOR, EditorShape)
  controlMap.set(ControlShapeEnum.PUBLISHTYPE, PublishTypeShape)
  return controlMap
}
