import {
  LockOutlined,
  UserOutlined,
} from '@ant-design/icons';
import {Alert, message} from 'antd';
import React, {useState} from 'react';
import ProForm, {ProFormCheckbox, ProFormText} from '@ant-design/pro-form';
import {useIntl, history, FormattedMessage, SelectLang, useModel} from 'umi';
import Footer from '@/components/Footer';
import {requestPost} from '@/services/api';

import styles from './Login.less';

const LoginMessage: React.FC<{
  content: string;
}> = ({content}) => (
  <Alert
    style={{
      marginBottom: 24,
    }}
    message={content}
    type="error"
    showIcon
  />
);

/** 此方法会跳转到 redirect 参数所在的位置 */
const redirect = () => {
  if (!history) return;
  setTimeout(() => {
    const {query} = history.location;
    const {redirect} = query as { redirect: string };
    history.push(redirect || '/');
  }, 10);
};

const Login: React.FC = () => {
  const [submitting, setSubmitting] = useState(false);
  const [userLoginState, setUserLoginState] = useState<Protocol.RestResult>({});
  const {initialState, setInitialState} = useModel('@@initialState');
  const intl = useIntl();

  const fetchUserInfo = async () => {
    const userInfo = await initialState?.fetchUserInfo?.();
    if (userInfo) {
      setInitialState({
        ...initialState,
        currentUser: userInfo,
      });
    }
  };

  const handleSubmit = async (values: any) => {
    setSubmitting(true);
    try {
      const result: Protocol.RestResult = await requestPost('/aio/shiro/login', values);
      if (result.success === true) {
        message.success('登录成功！');
        await fetchUserInfo();
        redirect();
        return;
      }
      // 如果失败去设置用户错误信息
      setUserLoginState(result);
    } catch (error) {
      message.error('登录失败，请重试！');
    }
    setSubmitting(false);
  };
  const {success: status} = userLoginState;

  return (
    <div className={styles.container}>
      <div className={styles.lang} data-lang>
        {SelectLang && <SelectLang/>}
      </div>
      <div className={styles.content}>
        <div className={styles.top}>
          <div className={styles.header}>
            <img alt="logo" className={styles.logo} src="/github.svg"/>
            <span className={styles.title}>All In One</span>
          </div>
          <div className={styles.desc}>
            https://github.com/stone0090/all-in-one
          </div>
        </div>

        <div className={styles.main}>
          <ProForm
            initialValues={{
              autoLogin: true,
            }}
            submitter={{
              searchConfig: {
                submitText: intl.formatMessage({
                  id: 'pages.login.submit',
                  defaultMessage: '登录',
                }),
              },
              render: (_, dom) => dom.pop(),
              submitButtonProps: {
                loading: submitting,
                size: 'large',
                style: {
                  width: '100%',
                },
              },
            }}
            onFinish={async (values) => {
              handleSubmit(values as API.LoginParams);
            }}
          >
            {status === false && (
              <LoginMessage
                content={intl.formatMessage({
                  id: 'pages.login.accountLogin.errorMessage',
                  defaultMessage: '账户或密码错误（admin/ant.design)',
                })}
              />
            )}
            <ProFormText
              name="username"
              fieldProps={{
                size: 'large',
                prefix: <UserOutlined className={styles.prefixIcon}/>,
              }}
              placeholder={intl.formatMessage({
                id: 'pages.login.username.placeholder',
                defaultMessage: '用户名: admin or usermgr',
              })}
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.login.username.required"
                      defaultMessage="请输入用户名!"
                    />
                  ),
                },
              ]}
            />
            <ProFormText.Password
              name="password"
              fieldProps={{
                size: 'large',
                prefix: <LockOutlined className={styles.prefixIcon}/>,
              }}
              placeholder={intl.formatMessage({
                id: 'pages.login.password.placeholder',
                defaultMessage: '密码: ant.design',
              })}
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage
                      id="pages.login.password.required"
                      defaultMessage="请输入密码！"
                    />
                  ),
                },
              ]}
            />
            <div
              style={{
                marginBottom: 24,
              }}
            >
              <ProFormCheckbox noStyle name="autoLogin">
                <FormattedMessage id="pages.login.rememberMe" defaultMessage="自动登录"/>
              </ProFormCheckbox>
              <a
                style={{
                  float: 'right',
                }}
              >
                <FormattedMessage id="pages.login.forgotPassword" defaultMessage="忘记密码"/>
              </a>
            </div>
          </ProForm>
        </div>
      </div>
      <Footer/>
    </div>
  );
};

export default Login;
