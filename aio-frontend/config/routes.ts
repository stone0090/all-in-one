export default [
  {
    path: '/login',
    layout: false,
    component: './Login',
  },
  {
    path: '/',
    redirect: '/Welcome',
  },
  {
    path: '/welcome',
    name: '欢迎',
    component: './Welcome',
  },
  {
    path: '/core',
    name: '算法开发',
    routes: [
      {
        path: '/core/operator',
        name: '算子管理',
        component: './core/Operator',
      },
    ],
  },
  {
    path: '/system',
    name: '系统管理',
    routes: [
      {
        path: '/system/user',
        name: '用户管理',
        component: './system/User',
      },
      {
        path: '/system/role',
        name: '角色管理',
        component: './system/Role',
      },
      {
        path: '/system/permission',
        name: '权限管理',
        component: './system/Permission',
      },
      {
        path: '/system/config',
        name: '配置管理',
        component: './system/Config',
      },
    ],
  },
  {
    component: './404',
  },
];
