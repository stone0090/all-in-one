import type {NsJsonSchemaForm} from '@antv/xflow'
import React, {useState} from 'react'
import {Form, message, Select} from "antd";
import {FormItemWrapper} from "@antv/xflow";

export const PublishTypeShape: React.FC<NsJsonSchemaForm.IControlProps> = props => {
  const {controlSchema} = props
  const {required, tooltip, extra, name, label, } = controlSchema
  if (controlSchema.value == 'SECOND_SCHEDULING') {
    message.warn('暂不支持【秒级调度】，已切换为【API服务】！');
  }
  return (
    <FormItemWrapper schema={controlSchema}>
      {({hidden, initialValue}) => {
        return (
          <div>
            <Form.Item
              name={name}
              label={label}
              initialValue={'API_SERVICE'}
              tooltip={tooltip}
              extra={extra}
              required={required}
              hidden={hidden}
            >
              <Select
                style={{width: '100%'}}
                options={
                  [
                    {value: 'API_SERVICE', label: 'API服务'},
                    // {value: 'SECOND_SCHEDULING', label: '秒级调度'}
                  ]}
                // value={selectedValue}
                // onChange={(value) => {
                //   if (value == 'SECOND_SCHEDULING') {
                //     message.warn('暂不支持秒级调度！');
                //     setSelectedValue('API_SERVICE');
                //   }
                // }}
              />
            </Form.Item>
            {/*<Form.Item*/}
            {/*  name="SchedulingPeriod"*/}
            {/*  label="调度周期(秒)"*/}
            {/*  initialValue={initialValue}*/}
            {/*  tooltip={tooltip}*/}
            {/*  extra={extra}*/}
            {/*  required={required}*/}
            {/*  hidden={hidden}*/}
            {/*>*/}
            {/*  <Input></Input>*/}
            {/*</Form.Item>*/}
          </div>
        )
      }}
    </FormItemWrapper>
  )
}
