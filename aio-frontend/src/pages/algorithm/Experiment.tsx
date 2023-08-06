import {ExclamationCircleOutlined, LinkOutlined, PlusOutlined} from '@ant-design/icons';
import {Drawer, Form, Button, Col, Row, Input, message, Spin, Select, Modal} from 'antd';
import React, {useState, useRef} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import type {ProColumns, ActionType} from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import {requestGet, requestPost} from '@/services/api';
import TextArea from "antd/es/input/TextArea";

import styles from './Operator.less';
import {Link} from "umi";

const Experiment: React.FC = () => {

  const actionRef = useRef<ActionType>();
  const [form] = Form.useForm();
  const [apiForm] = Form.useForm();
  const [addStatus, setAddStatus] = useState<boolean>(true);
  const [formVisible, setFormVisible] = useState<boolean>(false);
  const [invokeVisible, setInvokeVisible] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);
  type handleGetCallback = (record: any) => void;
  const {confirm} = Modal;

  const handleQuery = async (params: any) => {
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/experiment/list', params);
    return {
      data: result?.data?.list,
      total: result?.data?.total,
      success: result?.success
    }
  };

  const handleSave = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>(addStatus ? '/aio/experiment/add' : '/aio/experiment/editBrief', record);
    if (result?.success) {
      setFormVisible(false);
      message.success((addStatus ? '新建' : '修改') + '成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleGet = async (record: any, callback: handleGetCallback) => {
    setLoading(true);
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/experiment/get', {"id": record.id});
    if (result?.success) {
      callback(result.data);
    }
    setLoading(false);
  };

  const handleRemove = async (record: any) => {
    confirm({
      title: '确认删除',
      icon: <ExclamationCircleOutlined/>,
      content: '您确定要删除此画布吗？',
      okText: '确认',
      cancelText: '取消',
      onOk: async () => {
        const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/experiment/remove', {id: record.id});
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
      title: '画布名称',
      dataIndex: 'exName',
      valueType: 'textarea',
    },
    {
      title: '画布描述',
      dataIndex: 'exDesc',
      valueType: 'textarea',
    },
    {
      title: '画布状态',
      dataIndex: 'exStatusName',
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
      title: '画布操作',
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
        <Link to={"/algorithm/dag?id=" + record.id}>
          <span>算子编排</span>
        </Link>,
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
    <PageContainer>
      <ProTable
        headerTitle='画布列表'
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
            onClick={() => {
              form.resetFields();
              setFormVisible(true);
              setAddStatus(true);
            }}
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
              <Col span={24}>
                <Form.Item
                  name="exName"
                  label="画布名称"
                  rules={[{required: true}]}
                >
                  <Input placeholder="画布名称必须介于3到20位字符之间"/>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <Form.Item
                  name="exDesc"
                  label="画布描述"
                >
                  <TextArea showCount maxLength={200} autoSize={true} placeholder="画布描述必须小于200位字符"/>
                </Form.Item>
              </Col>
            </Row>
            <Form.Item name="id"></Form.Item>
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
  );
};

export default Experiment;
