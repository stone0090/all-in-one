import {PlusOutlined} from '@ant-design/icons';
import {Drawer, Form, Button, Col, Row, Input, message, Spin, Select, Radio} from 'antd';
import React, {useState, useRef} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import type {ProColumns, ActionType} from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import type {ProDescriptionsItemProps} from '@ant-design/pro-descriptions';
import ProDescriptions from '@ant-design/pro-descriptions';
import {requestGet, requestPost} from '@/services/api';
import CodeEditor from '@/components/CodeEditor';

const Operator: React.FC = () => {

  const actionRef = useRef<ActionType>();
  const [form] = Form.useForm();
  const [currentRow, setCurrentRow] = useState<any>();
  const [addStatus, setAddStatus] = useState<boolean>(true);
  const [formVisible, setFormVisible] = useState<boolean>(false);
  const [detailVisible, setDetailVisible] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);
  type handleGetCallback = (record: any) => void;

  const handleQuery = async (params: any) => {
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/operator/list', params);
    return {
      data: result?.data?.list,
      total: result?.data?.total,
      success: result?.success
    }
  };

  const handleAdd = async () => {
    form.resetFields();
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/operator/config/get');
    form.setFieldsValue(result?.data);
    setFormVisible(true);
    setAddStatus(true);
  };

  const handleGet = async (record: any, callback: handleGetCallback) => {
    setLoading(true);
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/operator/get', {"id": record.id});
    if (result?.success) {
      callback(result.data);
    }
    setLoading(false);
  };

  const handleSave = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>(addStatus ? '/aio/operator/add' : '/aio/operator/edit', record);
    if (result?.success) {
      setFormVisible(false);
      message.success((addStatus ? '新建' : '修改') + '成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleRemove = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/remove', {"id": record.id});
    if (result?.success) {
      setFormVisible(false);
      message.success('删除成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handlePublish = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/publish', {"id": record.id});
    if (result?.success) {
      setFormVisible(false);
      message.success('发布成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleInvoke = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/invoke', {"id": record.id});
    if (result?.success) {
      setFormVisible(false);
      message.success('调用成功！结果为：' + result.data);
    }
  };

  const columns: ProColumns[] = [
    {
      title: '算子标识',
      dataIndex: 'opCode',
      valueType: 'textarea',
      render: (dom, record) => {
        return (
          <a
            onClick={() => {
              setCurrentRow(undefined);
              handleGet(record, setCurrentRow);
              setDetailVisible(true);
            }}
          >
            {dom}
          </a>
        );
      },
    },
    {
      title: '算子名称',
      dataIndex: 'opName',
      valueType: 'textarea',
    },
    {
      title: '算子状态',
      dataIndex: 'isDisabled',
      valueType: 'textarea',
      search: false,
      render: (dom) => {
        return (
          <div>
            {dom == 1 ? '停用' : '启用'}
          </div>
        );
      },
    },
    {
      title: '更新时间',
      dataIndex: 'gmtModified',
      valueType: 'dateTime',
      search: false,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (dom, record) => [
        <a
          key="edit"
          onClick={() => {
            form.resetFields();
            setAddStatus(false);
            setDetailVisible(false);
            handleGet(record, form.setFieldsValue);
            setFormVisible(true);
          }}
        >
          修改
        </a>,
        <a
          key="publish"
          onClick={() => {
            handlePublish(record);
          }}
        >
          发布API
        </a>,
        <a
          key="invoke"
          onClick={() => {
            handleInvoke(record);
          }}
        >
          调试API
        </a>,
        <a>
          下线API
        </a>,
        <a
          key="remove"
          onClick={() => {
            handleRemove(record)
          }}
        >
          删除
        </a>,
      ],
    },
  ];


  return (
    <PageContainer>
      <ProTable
        headerTitle='算子列表'
        actionRef={actionRef}
        bordered={true}
        rowKey="id"
        search={{
          labelWidth: 60,
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={handleAdd}
          ><PlusOutlined/>
            新建
          </Button>
        ]}
        request={handleQuery}
        columns={columns}
        pagination={{
          pageSize: 8
        }}
      />
      <Drawer
        title={addStatus ? '新建' : '修改'}
        width={720}
        onClose={() => {
          setFormVisible(false);
        }}
        visible={formVisible}
        bodyStyle={{paddingBottom: 80}}
        footer={
          <div
            style={{
              textAlign: 'right',
            }}
          >
            <Button
              onClick={() => {
                setFormVisible(false);
              }}
              style={{marginRight: 8}}
            >
              取消
            </Button>
            <Button
              type="primary"
              onClick={() => {
                form.submit()
              }}
            >
              提交
            </Button>
          </div>
        }
      >
        <Spin spinning={loading}>
          <Form
            layout="vertical"
            form={form}
            onFinish={handleSave}
          >
            <Row gutter={16}>
              <Col span={12}>
                <Form.Item
                  name="opCode"
                  label="算子编码"
                  rules={[{required: true}]}
                >
                  <Input disabled={!addStatus} placeholder="3~20位字符，只能包含英文字母、数字、下划线"/>
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item
                  name="opName"
                  label="算子名称"
                  rules={[{required: true}]}
                >
                  <Input placeholder="1~20位字符"/>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={16}>
              <Col span={12}>
                <Form.Item
                  name="algoLanguage"
                  label="编程语言"
                  rules={[{required: true}]}
                >
                  <Select
                    style={{width: '100%'}}
                    options={[
                      {value: 'python', label: 'python'},
                      // {value: 'go', label: 'go'},
                      // {value: 'java', label: 'java'},
                      // {value: 'javascript', label: 'javascript'},
                      // {value: 'typescript', label: 'typescript'},
                    ]}
                  />
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item
                  name="isDisabled"
                  label="算子状态"
                  rules={[{required: true}]}
                >
                  <Radio.Group>
                    <Radio value={1}>停用</Radio>
                    <Radio value={0}>启用</Radio>
                  </Radio.Group>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <Form.Item
                  name="algoCode"
                  label="算子代码"
                  rules={[{required: true}]}
                >
                  <CodeEditor
                    height={200}
                    language={form.getFieldValue('algoLanguage') || 'python'}
                    value={form.getFieldValue('algoCode') || ''}
                    onChange={(value) => {
                      form.setFieldsValue({algoCode: value})
                    }}
                    formVisible={formVisible}
                  ></CodeEditor>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <Form.Item
                  name="inputParam"
                  label="算子入参"
                  rules={[{required: true}]}
                >
                  <CodeEditor
                    height={100}
                    language={'json'}
                    value={form.getFieldValue('inputParam') || '{}'}
                    onChange={(value) => {
                      form.setFieldsValue({inputParam: value})
                    }}
                    formVisible={formVisible}
                  ></CodeEditor>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <Form.Item
                  name="outputParam"
                  label="算子出参"
                  rules={[{required: true}]}
                >
                  <CodeEditor
                    height={100}
                    language={'json'}
                    value={form.getFieldValue('outputParam') || '{}'}
                    onChange={(value) => {
                      form.setFieldsValue({outputParam: value})
                    }}
                    formVisible={formVisible}
                  ></CodeEditor>
                </Form.Item>
              </Col>
            </Row>
            <Form.Item name="id"></Form.Item>
          </Form>
        </Spin>
      </Drawer>
      <Drawer
        width={720}
        visible={detailVisible}
        onClose={() => {
          setCurrentRow(undefined);
          setDetailVisible(false);
        }}
        closable={false}
      >
        <ProDescriptions<any>
          column={2}
          title="算子详情"
          request={async () => ({
            data: currentRow || {},
          })}
          params={{
            id: currentRow?.id,
          }}
          columns={columns as ProDescriptionsItemProps<any>[]}
        />
      </Drawer>
    </PageContainer>
  );
};

export default Operator;
