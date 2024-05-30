import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), meta: { role: 'user' }, hidden: true },
  { path: '/404', component: () => import('@/views/404'), meta: { role: 'user' }, hidden: true },
  {
    path: '/',
    redirect: '/index',
    component: Layout,
    meta: { role: 'admin' },
    children: [{
      path: 'index',
      name: 'Overview',
      component: () => import('@/views/overview/index'),
      meta: { title: '技术栈', icon: 'form' }
    }]
  },

  {
    path: '/user',
    component: Layout,
    meta: { role: 'admin' },
    children: [{
      path: 'index',
      name: 'User',
      component: () => import('@/views/user/index'),
      meta: { title: '用户管理', icon: 'form' }
    }]
  },

  {
    path: '/company',
    component: Layout,
    meta: { role: 'admin' },
    children: [{
      path: 'index',
      name: 'Company',
      component: () => import('@/views/company/index'),
      meta: { title: '公司管理', icon: 'form' }
    }]
  },
  {
    path: '/companyInfo',
    component: Layout,
    meta: { role: 'company' },
    children: [{
      path: 'index',
      name: 'CompanyInfo',
      component: () => import('@/views/company/companyInfo'),
      meta: { title: '公司信息', icon: 'form' }
    }]
  },
  {
    path: '/employment',
    component: Layout,
    meta: { role: 'admin' },
    children: [{
      path: 'index',
      name: 'Employment',
      component: () => import('@/views/employment/index'),
      meta: { title: '招聘职位管理', icon: 'form' }
    }]
  },
  {
    path: '/occupation',
    component: Layout,
    meta: { role: 'company' },
    children: [
      {
        path: 'index',
        name: 'Occupation',
        component: () => import('@/views/employment/occupation'),
        meta: { title: '职位管理', icon: 'form' }
      },
      {
        path: 'Add',
        name: 'AddOccupatiob',
        hidden: true,
        component: () => import('@/views/employment/addOccupation'),
        meta: { title: '新建职位', icon: 'form' }
      }
    ]
  },
  // {
  //   path: '/feedback',
  //   component: Layout,
  //   meta: { role: 'admin' },
  //   children: [{
  //     path: 'index',
  //     name: 'Feedback',
  //     component: () => import('@/views/feedback/index'),
  //     meta: { title: '反馈管理', icon: 'form' }
  //   }]
  // },
  {
    path: '/employmentUser',
    component: Layout,
    meta: { role: 'company' },
    children: [{
      path: 'index',
      name: 'EmploymentUser',
      component: () => import('@/views/employmentUser/index'),
      meta: { title: '投递简历管理', icon: 'form' }
    }]
  },
  {
    path: '/personalInfo',
    component: Layout,
    meta: { role: 'user' },
    children: [{
      path: 'index',
      name: 'PersonalInfo',
      component: () => import('@/views/user/personalInfo'),
      meta: { title: '个人信息管理', icon: 'form' }
    }]
  },
  { path: '*', redirect: '/404', meta: { role: 'user' }, hidden: true }
]

export default new Router({
  mode: 'history', // 后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
