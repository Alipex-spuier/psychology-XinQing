import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      component: () => import('./views/Login.vue'),
      name: '',
      hidden: true
    },
    {
      path: '/404',
      component: () => import('./views/404.vue'),
      name: '',
      hidden: true
    },
    {
      path: '/',
      component: () => import('./views/Home.vue'),
      name: '账号管理',
      iconCls: 'fa fa-id-card-o', // 图标样式class
      children: [
        // { path: '/main', component: () => import('./views/Main.vue'), name: '主页', hidden: true },
        { path: '/user', component: () => import('./views/nav1/users.vue'), name: '用户管理' },
        { path: '/expert', component: () => import('./views/nav1/expert.vue'), name: '专家管理' },
        { path: '/appointment', component: () => import('./views/nav1/appointment.vue'), name: '预约管理' }

        // { path: '/user', component: () => import('./views/nav1/user.vue'), name: '列表' }
      ]
    },
    // {
    //   path: '/',
    //   component: () => import('./views/Home.vue'),
    //   name: '文章管理',
    //   iconCls: 'el-icon-message', // 图标样式class
    //   children: [
    //     { path: '/table', component: () => import('./views/nav1/Table.vue'), name: '首页资讯管理' },
    //     { path: '/form', component: () => import('./views/nav1/Form.vue'), name: '专家文章管理' }
    //   ]
    // },
    // {
    //   path: '/',
    //   component: () => import('./views/Home.vue'),
    //   name: '工具管理',
    //   iconCls: 'fa fa-bar-chart', // 图标样式class
    //   children: [
    //     { path: '/table', component: () => import('./views/nav1/Table.vue'), name: '音乐管理' },
    //     { path: '/form', component: () => import('./views/nav1/Form.vue'), name: '问卷管理' }
    //   ]
    // },
    // {
    //   path: '/',
    //   component: () => import('./views/Home.vue'),
    //   name: '导航二',
    //   iconCls: 'fa fa-id-card-o',
    //   children: [
    //     { path: '/page4', component: () => import('./views/nav2/Page4.vue'), name: '页面4' },
    //     { path: '/page5', component: () => import('./views/nav2/Page5.vue'), name: '页面5' }
    //   ]
    // },
    // {
    //   path: '/',
    //   component: () => import('./views/Home.vue'),
    //   name: '',
    //   iconCls: 'fa fa-address-card',
    //   leaf: true,
    //   children: [
    //     { path: '/page6', component: () => import('./views/nav3/Page6.vue'), name: '导航三' }
    //   ]
    // },
    // {
    //   path: '/',
    //   component: () => import('./views/Home.vue'),
    //   name: 'Charts',
    //   iconCls: 'fa fa-bar-chart',
    //   children: [
    //     { path: '/echarts', component: () => import('./views/charts/echarts.vue'), name: 'echarts' }
    //   ]
    // },
    {
      path: '*',
      hidden: true,
      redirect: { path: '/404' }
    }
  ]
})
