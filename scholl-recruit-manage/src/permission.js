import router from './router'

import { getToken } from '@/utils/auth'

const whiteList = ['/login'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  // console.log(getToken())

  if (getToken()) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      // if (JSON.parse(localStorage.getItem('userInfo')).role[0].authority === 'Enterprise_User') {
      //   // next({ path: '/companyInfo' })
      //   next()
      // } else {
      next()
      // }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      // NProgress.done()
    }
  }
})

