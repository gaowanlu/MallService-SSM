import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { DEFAULT_ROUTES } from './store/modules/permission'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/auth-redirect'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  NProgress.start() // start progress bar
  if (to.meta.title) {
    document.title = to.meta.title + '-' + process.env.SITE_NAME
  } else {
    document.title = process.env.SITE_NAME
  }
  store.dispatch('GetUserInfo').catch(() => {
    if (to.path !== '/login') {
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }).finally(() => {
    if (store.state.user.login) {
      // user login
      if (to.path === '/login') {
        next({ path: '/' })
        NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
      } else if (!store.getters.loaded) {
        const asyncRouterMap = DEFAULT_ROUTES
        store.dispatch('GenerateRoutes', { asyncRouterMap }).then(() => {
          // 根据roles权限生成可访问的路由表
          router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
          // next({ ...to, replace: true })
          next({ ...to, replace: true })
        })
      } else {
        next()
      }
    } else {
      if (whiteList.indexOf(to.path) !== -1) {
        // 在免登录白名单，直接进入
        next()
      } else {
        next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
        NProgress.done() // if current page is login will not trigger afterEach hook, so manually handle it
      }
    }
  })
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
