import { constantRouterMap } from '@/router'
import Layout from '@/views/Layout/Layout'
// 模板
export const componentsMap = {
  Dashboard: () => import('@/views/Dashboard/index'),
  Admin: () => import('@/views/UserManagement/Admin/index'),
  AdminList: () => import('@/views/UserManagement/Admin/list'),
  AdminLog: () => import('@/views/UserManagement/Admin/log'),
  Manage: () => import('@/views/UserManagement/Manage/index'),
  ManageList: () => import('@/views/UserManagement/Manage/list'),
  Member: () => import('@/views/UserManagement/Member/index'),
  MemberList: () => import('@/views/UserManagement/Member/list'),
  Power: () => import('@/views/UserManagement/Power/index'),
  PowerList: () => import('@/views/UserManagement/Power/list'),
  Category: () => import('@/views/CommodityManagement/Category/index'),
  CategoryList: () => import('@/views/CommodityManagement/Category/list'),
  Good: () => import('@/views/CommodityManagement/Good/index'),
  GoodList: () => import('@/views/CommodityManagement/Good/list'),
  GoodCreate: () => import('@/views/CommodityManagement/Good/create'),
  GoodEdit: () => import('@/views/CommodityManagement/Good/edit'),
  GoodDetail: () => import('@/views/CommodityManagement/Good/detail'),
  Indent: () => import('@/views/IndentManagement/Indent/index'),
  IndentList: () => import('@/views/IndentManagement/Indent/list'),
  IndentDetail: () => import('@/views/IndentManagement/Indent/detail'),
  IndentShipment: () => import('@/views/IndentManagement/Indent/shipment')
}
const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: [],
    loaded: false
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    },
    SET_LOADED: (state, loaded) => {
      state.loaded = loaded
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const { asyncRouterMap } = data
        const accessedRouters = generateAsyncRouter(componentsMap, asyncRouterMap)
        accessedRouters.push({ path: '*', redirect: '/404', hidden: true })
        commit('SET_ROUTERS', accessedRouters)
        commit('SET_LOADED', true)
        // console.log(accessedRouters)
        resolve()
      })
    }
  }
}
function generateAsyncRouter(componentsMap, serverRouterMap) {
  serverRouterMap.forEach(function(item, index) {
    if (!item.redirect) {
      delete item.redirect
    }
    item.component = item.component === 'Layout' ? Layout : componentsMap[item.component]
    if (item.children && item.children.length > 0) {
      generateAsyncRouter(componentsMap, item.children)
    }
  })
  return serverRouterMap
}
export default permission

export const DEFAULT_ROUTES = [
  {
    id: 29,
    pid: 0,
    path: '/commodityManagement',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'CommodityManagement',
    hidden: false,
    meta: {
      title: '商品管理',
      icon: 'goods',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 116,
        pid: 29,
        path: 'good',
        component: 'Good',
        redirect: '',
        alwaysShow: false,
        name: 'Good',
        hidden: false,
        meta: {
          title: '商品',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 117,
            pid: 116,
            path: 'goodList',
            component: 'GoodList',
            redirect: '',
            alwaysShow: false,
            name: 'GoodList',
            hidden: false,
            meta: {
              title: '商品列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 118,
            pid: 116,
            path: 'goodCreate',
            component: 'GoodCreate',
            redirect: '',
            alwaysShow: false,
            name: 'GoodCreate',
            hidden: true,
            meta: {
              title: '创建商品',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 119,
            pid: 116,
            path: 'goodEdit',
            component: 'GoodEdit',
            redirect: '',
            alwaysShow: false,
            name: 'GoodEdit',
            hidden: true,
            meta: {
              title: '保存商品',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 120,
            pid: 116,
            path: 'goodDestroy',
            component: 'GoodDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'GoodDestroy',
            hidden: true,
            meta: {
              title: '删除商品',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 195,
            pid: 116,
            path: 'goodDetail',
            component: 'GoodDetail',
            redirect: '',
            alwaysShow: false,
            name: 'GoodDetail',
            hidden: true,
            meta: {
              title: '商品详情',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 95,
        pid: 29,
        path: 'category',
        component: 'Category',
        redirect: '',
        alwaysShow: false,
        name: 'Category',
        hidden: false,
        meta: {
          title: '分类',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 96,
            pid: 95,
            path: 'categoryList',
            component: 'CategoryList',
            redirect: '',
            alwaysShow: false,
            name: 'CategoryList',
            hidden: false,
            meta: {
              title: '分类列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 97,
            pid: 95,
            path: 'categoryCreate',
            component: 'CategoryCreate',
            redirect: '',
            alwaysShow: false,
            name: 'CategoryCreate',
            hidden: true,
            meta: {
              title: '创建分类',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 98,
            pid: 95,
            path: 'categoryEdit',
            component: 'CategoryEdit',
            redirect: '',
            alwaysShow: false,
            name: 'CategoryEdit',
            hidden: true,
            meta: {
              title: '保存分类',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 99,
            pid: 95,
            path: 'categoryDestroy',
            component: 'CategoryDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'CategoryDestroy',
            hidden: true,
            meta: {
              title: '删除分类',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      }
    ]
  },
  {
    id: 127,
    pid: 0,
    path: '/indentManagement',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'IndentManagement',
    hidden: false,
    meta: {
      title: '订单管理',
      icon: 'Indent',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 126,
        pid: 127,
        path: 'indentShipment',
        component: 'IndentShipment',
        redirect: '',
        alwaysShow: false,
        name: 'IndentShipment',
        hidden: true,
        meta: {
          title: '发货',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 128,
        pid: 127,
        path: 'indentList',
        component: 'IndentList',
        redirect: '',
        alwaysShow: false,
        name: 'IndentList',
        hidden: false,
        meta: {
          title: '订单列表',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 133,
        pid: 127,
        path: 'indentDetail',
        component: 'IndentDetail',
        redirect: '',
        alwaysShow: false,
        name: 'IndentDetail',
        hidden: true,
        meta: {
          title: '订单详情',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 148,
        pid: 127,
        path: 'indentRefund',
        component: 'IndentRefund',
        redirect: '',
        alwaysShow: false,
        name: 'IndentRefund',
        hidden: true,
        meta: {
          title: '退款',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 196,
        pid: 127,
        path: 'indentDhl',
        component: 'IndentDhl',
        redirect: '',
        alwaysShow: false,
        name: 'IndentDhl',
        hidden: true,
        meta: {
          title: '保存订单配送信息',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      }
    ]
  },
  {
    id: 2,
    pid: 0,
    path: '/userManagement',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'UserManagement',
    hidden: false,
    meta: {
      title: '用户管理',
      icon: 'user',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 6,
        pid: 2,
        path: 'admin',
        component: 'Admin',
        redirect: '',
        alwaysShow: false,
        name: 'Admin',
        hidden: false,
        meta: {
          title: '管理员管理',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 7,
            pid: 6,
            path: 'adminCreate',
            component: 'AdminCreate',
            redirect: null,
            alwaysShow: false,
            name: 'AdminCreate',
            hidden: true,
            meta: {
              title: '创建管理员',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 8,
            pid: 6,
            path: 'adminEdit',
            component: 'AdminEdit',
            redirect: null,
            alwaysShow: false,
            name: 'AdminEdit',
            hidden: true,
            meta: {
              title: '保存管理员',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 9,
            pid: 6,
            path: 'adminDestroy',
            component: 'AdminDestroy',
            redirect: null,
            alwaysShow: false,
            name: 'AdminDestroy',
            hidden: true,
            meta: {
              title: '删除管理员',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 187,
            pid: 6,
            path: 'adminList',
            component: 'AdminList',
            redirect: '',
            alwaysShow: false,
            name: 'AdminList',
            hidden: false,
            meta: {
              title: '管理员列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 10,
        pid: 2,
        path: 'member',
        component: 'Member',
        redirect: '',
        alwaysShow: false,
        name: 'Member',
        hidden: false,
        meta: {
          title: '会员管理',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 154,
            pid: 10,
            path: 'memberCreate',
            component: 'MemberCreate',
            redirect: '',
            alwaysShow: false,
            name: 'MemberCreate',
            hidden: true,
            meta: {
              title: '创建用户',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 155,
            pid: 10,
            path: 'memberEdit',
            component: 'MemberEdit',
            redirect: '',
            alwaysShow: false,
            name: 'MemberEdit',
            hidden: true,
            meta: {
              title: '保存用户',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 189,
            pid: 10,
            path: 'memberList',
            component: 'MemberList',
            redirect: '',
            alwaysShow: false,
            name: 'MemberList',
            hidden: false,
            meta: {
              title: '会员列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 15,
        pid: 2,
        path: 'manage',
        component: 'Manage',
        redirect: '',
        alwaysShow: false,
        name: 'Manage',
        hidden: false,
        meta: {
          title: '管理组管理',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 16,
            pid: 15,
            path: 'manageCreate',
            component: 'ManageCreate',
            redirect: null,
            alwaysShow: false,
            name: 'ManageCreate',
            hidden: true,
            meta: {
              title: '创建管理组',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 17,
            pid: 15,
            path: 'manageEdit',
            component: 'ManageEdit',
            redirect: null,
            alwaysShow: false,
            name: 'ManageEdit',
            hidden: true,
            meta: {
              title: '保存管理组',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 18,
            pid: 15,
            path: 'manageDestroy',
            component: 'ManageDestroy',
            redirect: null,
            alwaysShow: false,
            name: 'ManageDestroy',
            hidden: true,
            meta: {
              title: '删除管理组',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 190,
            pid: 15,
            path: 'manageList',
            component: 'ManageList',
            redirect: '',
            alwaysShow: false,
            name: 'ManageList',
            hidden: false,
            meta: {
              title: '管理组列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 19,
        pid: 2,
        path: 'power',
        component: 'Power',
        redirect: '',
        alwaysShow: false,
        name: 'Power',
        hidden: false,
        meta: {
          title: '权限管理',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 20,
            pid: 19,
            path: 'powerCreate',
            component: 'PowerCreate',
            redirect: null,
            alwaysShow: false,
            name: 'PowerCreate',
            hidden: true,
            meta: {
              title: '创建权限',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 21,
            pid: 19,
            path: 'powerEdit',
            component: 'PowerEdit',
            redirect: null,
            alwaysShow: false,
            name: 'PowerEdit',
            hidden: true,
            meta: {
              title: '保存权限',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 22,
            pid: 19,
            path: 'powerDestroy',
            component: 'PowerDestroy',
            redirect: null,
            alwaysShow: false,
            name: 'PowerDestroy',
            hidden: true,
            meta: {
              title: '删除权限',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 191,
            pid: 19,
            path: 'powerList',
            component: 'PowerList',
            redirect: '',
            alwaysShow: false,
            name: 'PowerList',
            hidden: false,
            meta: {
              title: '权限列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      }
    ]
  }
]
