import {ExclamationCircleOutlined, LinkOutlined, PlusOutlined} from '@ant-design/icons';
import {Drawer, Form, Button, Col, Row, Input, message, Spin, Select, Modal} from 'antd';
import React, {useState, useRef} from 'react';
import {PageContainer} from '@ant-design/pro-layout';
import type {ProColumns, ActionType} from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import {requestGet, requestPost} from '@/services/api';
import TextArea from "antd/es/input/TextArea";
import {Link} from "umi";
import styles from './common.less';


const Dag: React.FC = () => {

  const actionRef = useRef<ActionType>();
  const [form] = Form.useForm();
  const [svcForm] = Form.useForm();
  const [addStatus, setAddStatus] = useState<boolean>(true);
  const [formVisible, setFormVisible] = useState<boolean>(false);
  const [invokeVisible, setInvokeVisible] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);
  const [svcLoading, setSvcLoading] = useState<boolean>(false);
  type handleGetCallback = (record: any) => void;
  const {confirm} = Modal;

  const handleQuery = async (params: any) => {
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/dag/list', params);
    return {
      data: result?.data?.list,
      total: result?.data?.total,
      success: result?.success
    }
  };

  const handleSave = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>(addStatus ? '/aio/dag/add' : '/aio/dag/editBrief', record);
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
    const result: Protocol.RestResult = await requestGet<Protocol.RestResult>('/aio/dag/get', {"id": record.id});
    if (result?.success) {
      callback(result.data);
    }
    setLoading(false);
  };

  const handleRemove = async (record: any) => {
    confirm({
      title: '确认删除',
      icon: <ExclamationCircleOutlined/>,
      content: '您确定要删除此Dag吗？',
      okText: '确认',
      cancelText: '取消',
      onOk: async () => {
        const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/dag/remove', {id: record.id});
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


  const handleOnlineSvc = async (record: any) => {
    setSvcLoading(true);
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/dag/onlineSvc', {
      "id": record.id,
    });
    setSvcLoading(false);
    if (result?.success) {
      message.success('发布成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleOfflineSvc = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/dag/offlineSvc', {
      "id": record.id,
    });
    if (result?.success) {
      message.success('下线成功！');
      if (actionRef.current) {
        actionRef.current.reload();
      }
    }
  };

  const handleInvokeSvc = async (record: any) => {
    const result: Protocol.RestResult = await requestPost<Protocol.RestResult>('/aio/dag/invokeSvc', {
      "id": record.id,
      "svcType": 'OPERATOR',
      "inputParam": record.inputParam
    });
    if (result?.success) {
      message.success('调用成功！');
      svcForm.setFieldsValue({outputParam: result.data})
    }
  };

  const columns: ProColumns[] = [
    {
      title: 'Dag名称',
      dataIndex: 'dagName',
      valueType: 'textarea',
    },
    {
      title: 'Dag描述',
      dataIndex: 'dagDesc',
      valueType: 'textarea',
    },
    {
      title: 'Dag状态',
      dataIndex: 'dagStatusName',
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
      title: 'Dag操作',
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
        <Link to={"/algorithm/xflow-dag?id=" + record.id} target="_blank">
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
      title: '服务状态',
      dataIndex: 'svcStatusName',
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
      title: '服务操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (dom, record) => [
        <a
          key="online"
          onClick={() => {
            handleOnlineSvc(record);
          }}
        >
          服务上线
        </a>,
        <a
          key="offline"
          onClick={() => {
            handleOfflineSvc(record);
          }}
        >
          服务下线
        </a>,
        <a
          key="invoke"
          onClick={() => {
            svcForm.resetFields();
            svcForm.setFieldsValue({id: record.id})
            svcForm.setFieldsValue({svcUrl: record.svcUrl})
            svcForm.setFieldsValue({inputParam: record.inputParam})
            setInvokeVisible(true);
          }}
        >
          服务调用
        </a>,
      ],
    },
  ];

  return (
    <Spin spinning={svcLoading}>
      <PageContainer>
        <ProTable
          headerTitle='Dag列表'
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
                    name="dagName"
                    label="Dag名称"
                    rules={[{required: true}]}
                  >
                    <Input placeholder="Dag名称必须介于3到20位字符之间"/>
                  </Form.Item>
                </Col>
              </Row>
              <Row gutter={16}>
                <Col span={24}>
                  <Form.Item
                    name="dagDesc"
                    label="Dag描述"
                  >
                    <TextArea showCount maxLength={200} autoSize={true} placeholder="Dag描述必须小于200位字符"/>
                  </Form.Item>
                </Col>
              </Row>
              <Form.Item name="id"></Form.Item>
            </Form>
          </Spin>
        </Drawer>
        <Drawer
          title={'服务调用'}
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
                  svcForm.submit()
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
              form={svcForm}
              onFinish={handleInvokeSvc}
            >
              <Row gutter={16}>
                <Col span={24}>
                  <Form.Item
                    name="svcUrl"
                    label="服务地址"
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

export default Dag;
