import {ExclamationCircleOutlined, PlusOutlined} from '@ant-design/icons';
import {Drawer, Form, Button, Col, Row, Input, message, Spin, Select, Modal} from 'antd';
import React, {useState, useRef} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import type {ProColumns, ActionType} from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import {requestGet, requestPost} from '@/services/api';
import CodeEditor from '@/components/CodeEditor';
import TextArea from "antd/es/input/TextArea";

import styles from './Operator.less';

const Operator: React.FC = () => {

  const actionRef = useRef<ActionType>();
  const [form] = Form.useForm();
  const [apiForm] = Form.useForm();
  const [addStatus, setAddStatus] = useState<boolean>(true);
  const [formVisible, setFormVisible] = useState<boolean>(false);
  const [invokeVisible, setInvokeVisible] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);
  const [apiLoading, setApiLoading] = useState<boolean>(false);
  type handleGetCallback = (record: any) => void;
  const {confirm} = Modal;

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
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/operator/getDefaultConfig');
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

  const handlePublish = async (record: any) => {
    confirm({
      title: '确认上架',
      icon: <ExclamationCircleOutlined/>,
      content: '算子上架后不能再修改，但可以在画布中引用，您确定要上架此算子吗？',
      okText: '确认',
      cancelText: '取消',
      onOk: async () => {
        const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/publish', {id: record.id});
        if (result?.success) {
          setFormVisible(false);
          message.success('上架成功！');
          if (actionRef.current) {
            actionRef.current.reload();
          }
        }
      },
    });
  };

  const handleDeprecate = async (record: any) => {
    confirm({
      title: '确认废弃',
      icon: <ExclamationCircleOutlined/>,
      content: '算子废弃后不能再重新上架，也不能在画布中引用，您确定要废弃此算子吗？',
      okText: '确认',
      cancelText: '取消',
      onOk: async () => {
        const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/deprecate', {id: record.id});
        if (result?.success) {
          setFormVisible(false);
          message.success('废弃成功！');
          if (actionRef.current) {
            actionRef.current.reload();
          }
        }
      },
    });
  };

  const handleRemove = async (record: any) => {
    confirm({
      title: '确认删除',
      icon: <ExclamationCircleOutlined/>,
      content: '您确定要删除此算子吗？',
      okText: '确认',
      cancelText: '取消',
      onOk: async () => {
        const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/remove', {id: record.id});
        if (result?.success) {
          setFormVisible(false);
          message.success('删除成功！');
          if (actionRef.current) {
            actionRef.current.reload();
          }
        }
      },
    });
  };


  const handleOnlineApi = async (record: any) => {
    setApiLoading(true);
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/onlineApi', {
      "id": record.id,
      "apiType": 'OPERATOR',
    });
    setApiLoading(false);
    if (result?.success) {
      message.success('发布成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleOfflineApi = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/offlineApi', {
      "id": record.id,
      "apiType": 'OPERATOR',
    });
    if (result?.success) {
      message.success('下线成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleInvokeApi = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/operator/invokeApi', {
      "id": record.id,
      "apiType": 'OPERATOR',
      "inputParam": record.inputParam
    });
    if (result?.success) {
      message.success('调用成功！');
      apiForm.setFieldsValue({outputParam: result.data})
    }
  };

  const columns: ProColumns[] = [
    {
      title: '算子标识',
      dataIndex: 'opCode',
      valueType: 'textarea',
    },
    {
      title: '算子名称',
      dataIndex: 'opName',
      valueType: 'textarea',
    },
    {
      title: '算子状态',
      dataIndex: 'opStatusName',
      valueType: 'textarea',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'gmtModified',
      valueType: 'dateTime',
      search: false,
    },
    {
      title: '算子操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (dom, record) => [
        <a
          key="edit"
          onClick={() => {
            form.resetFields();
            setAddStatus(false);
            handleGet(record, form.setFieldsValue);
            setFormVisible(true);
          }}
        >
          修改
        </a>,
        <a
          key="publish"
          className={record.opStatus != 'INIT' ? styles.disabled : ''}
          onClick={() => {
            if (record?.opStatus == 'INIT') {
              handlePublish(record);
            }
          }}
        >
          上架
        </a>,
        <a
          key="deprecate"
          className={record.opStatus != 'PUBLISHED' ? styles.disabled : ''}
          onClick={() => {
            if (record?.opStatus == 'PUBLISHED') {
              handleDeprecate(record);
            }
          }}
        >
          废弃
        </a>,
        <a
          key="remove"
          onClick={() => {
            handleRemove(record);
          }}
        >
          删除
        </a>,
      ],
    },
    {
      title: 'API状态',
      dataIndex: 'apiStatusName',
      valueType: 'textarea',
      search: false,
      render: (dom) => {
        return (
          <div>
            {dom || '-'}
          </div>
        );
      },
    },
    {
      title: 'API操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (dom, record) => [
        <a
          key="online"
          onClick={() => {
            handleOnlineApi(record);
          }}
        >
          API上线
        </a>,
        <a
          key="offline"
          onClick={() => {
            handleOfflineApi(record);
          }}
        >
          API下线
        </a>,
        <a
          key="invoke"
          onClick={() => {
            apiForm.resetFields();
            apiForm.setFieldsValue({id: record.id})
            apiForm.setFieldsValue({apiUrl: record.apiUrl})
            apiForm.setFieldsValue({inputParam: record.inputParam})
            setInvokeVisible(true);
          }}
        >
          API调用
        </a>,
      ],
    },
  ];

  return (
    <Spin spinning={apiLoading}>
      <PageContainer>
        <ProTable
          headerTitle='算子列表'
          actionRef={actionRef}
          bordered={true}
          rowKey="id"
          search={{
            labelWidth: 65,
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
                textAlign: 'left',
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
                disabled={form.getFieldValue('opStatus') && form.getFieldValue('opStatus') != 'INIT'}
                onClick={() => {
                  form.submit()
                }}
              >
                提交
              </Button>
              {form.getFieldValue('opStatus') != ''}
              <span className={styles.warning}>{
                !form.getFieldValue('opStatus') || form.getFieldValue('opStatus') == 'INIT'
                  ? ''
                  : (form.getFieldValue('opStatusName') + "的算子不允许修改！")
              }</span>
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
                    label="算子标识"
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
                  {/*<Form.Item*/}
                  {/*  name="isDisabled"*/}
                  {/*  label="算子状态"*/}
                  {/*  rules={[{required: true}]}*/}
                  {/*>*/}
                  {/*  <Radio.Group>*/}
                  {/*    <Radio value={1}>停用</Radio>*/}
                  {/*    <Radio value={0}>启用</Radio>*/}
                  {/*  </Radio.Group>*/}
                  {/*</Form.Item>*/}
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
                      height={300}
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
                    <TextArea showCount maxLength={1000} autoSize={true}/>
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
                    <TextArea showCount maxLength={1000} autoSize={true}/>
                  </Form.Item>
                </Col>
              </Row>
              <Form.Item name="id"></Form.Item>
              <Form.Item name="opStatus"></Form.Item>
              <Form.Item name="opStatusName"></Form.Item>
            </Form>
          </Spin>
        </Drawer>
        <Drawer
          title={'API调用'}
          width={720}
          onClose={() => {
            setInvokeVisible(false);
          }}
          visible={invokeVisible}
          bodyStyle={{paddingBottom: 80}}
          footer={
            <div
              style={{
                textAlign: 'left',
              }}
            >
              <Button
                onClick={() => {
                  setInvokeVisible(false);
                }}
                style={{marginRight: 8}}
              >
                取消
              </Button>
              <Button
                type="primary"
                onClick={() => {
                  apiForm.submit()
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
              form={apiForm}
              onFinish={handleInvokeApi}
            >
              <Row gutter={16}>
                <Col span={24}>
                  <Form.Item
                    name="apiUrl"
                    label="API地址"
                  >
                    <TextArea showCount maxLength={1000} autoSize={true}/>
                  </Form.Item>
                </Col>
              </Row>
              <Row gutter={16}>
                <Col span={24}>
                  <Form.Item
                    name="inputParam"
                    label="算子入参"
                  >
                    <TextArea showCount maxLength={1000} autoSize={true}/>
                  </Form.Item>
                </Col>
              </Row>
              <Row gutter={16}>
                <Col span={24}>
                  <Form.Item
                    name="outputParam"
                    label="算子出参"
                  >
                    <TextArea showCount maxLength={1000} autoSize={true}/>
                  </Form.Item>
                </Col>
              </Row>
              <Form.Item name="id"></Form.Item>
            </Form>
          </Spin>
        </Drawer>
      </PageContainer>
    </Spin>
  );
};

export default Operator;
