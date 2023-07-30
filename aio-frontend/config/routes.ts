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
    path: '/algorithm',
    name: '算法开发',
    routes: [
      {
        path: '/algorithm/operator',
        name: '算子上架',
        component: './algorithm/Operator',
      },
      {
        path: '/algorithm/experiment',
        name: '算子编排',
        component: './algorithm/Experiment',
      },
      {
        path: '/algorithm/op_group',
        name: '算子分组（待规划）',
        component: './404',
      },
      {
        path: '/algorithm/service',
        name: '服务列表（待规划）',
        component: './404',
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
      // {
      //   path: '/system/role',
      //   name: '角色管理',
      //   component: './system/Role',
      // },
      // {
      //   path: '/system/permission',
      //   name: '权限管理',
      //   component: './system/Permission',
      // },
      {
        path: '/system/config',
        name: '配置管理',
        component: './system/Config',
      },
    ],
  },
  {
    path: '/demo',
    name: '功能示例',
    routes: [
      {
        path: '/demo/xflow',
        name: '流程编排',
        component: './demo/XFlow',
      },
    ],
  },
  {
    component: './404',
  },
];
