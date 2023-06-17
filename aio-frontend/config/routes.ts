export default [
  {
    path: '/login',
    layout: false,
    component: './login',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    path: '/welcome',
    name: '欢迎',
    component: './welcome',
  },
  {
    path: '/usermgr',
    name: '用户管理',
    routes: [
      {
        path: '/usermgr/user',
        name: '用户列表',
        component: './usermgr/user',
      },
      {
        path: '/usermgr/role',
        name: '角色列表',
        component: './usermgr/role',
      },
      {
        path: '/usermgr/permission',
        name: '权限列表',
        component: './usermgr/permission',
      },
    ],
  },
  {
    path: '/system',
    name: '系统管理',
    routes: [
      {
        path: '/system/config',
        name: '配置列表',
        component: './system/config',
      },
    ],
  },
  {
    component: './404',
  },
];
