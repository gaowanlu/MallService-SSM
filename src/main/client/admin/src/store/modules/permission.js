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
  Brand: () => import('@/views/CommodityManagement/Brand/index'),
  BrandList: () => import('@/views/CommodityManagement/Brand/list'),
  Specification: () => import('@/views/CommodityManagement/Specification/index'),
  SpecificationList: () => import('@/views/CommodityManagement/Specification/list'),
  SpecificationGroup: () => import('@/views/CommodityManagement/SpecificationGroup/index'),
  SpecificationGroupList: () => import('@/views/CommodityManagement/SpecificationGroup/list'),
  Freight: () => import('@/views/LogisticManagement/Freight/index'),
  FreightList: () => import('@/views/LogisticManagement/Freight/list'),
  FreightCreate: () => import('@/views/LogisticManagement/Freight/create'),
  FreightEdit: () => import('@/views/LogisticManagement/Freight/edit'),
  Dhl: () => import('@/views/LogisticManagement/Dhl/index'),
  DhlList: () => import('@/views/LogisticManagement/Dhl/list'),
  Indent: () => import('@/views/IndentManagement/Indent/index'),
  IndentList: () => import('@/views/IndentManagement/Indent/list'),
  IndentDetail: () => import('@/views/IndentManagement/Indent/detail'),
  IndentShipment: () => import('@/views/IndentManagement/Indent/shipment'),
  RedisService: () => import('@/views/ToolManagement/RedisService/index'),
  RedisServiceList: () => import('@/views/ToolManagement/RedisService/list'),
  RedisPanel: () => import('@/views/ToolManagement/RedisService/panel'),
  Resource: () => import('@/views/ToolManagement/Resource/index'),
  ResourceList: () => import('@/views/ToolManagement/Resource/list'),
  // 轮播
  Banner: () => import('@/views/ToolManagement/Banner/index'),
  BannerList: () => import('@/views/ToolManagement/Banner/list'),
  // 统计
  StatisticsVisit: () => import('@/views/Statistics/visit'),
  StatisticsAgeAndSex: () => import('@/views/Statistics/user'),
  StatisticsPay: () => import('@/views/Statistics/pay'),
  // 插件列表
  // 插件
  PlugIn: () => import('@/views/Plugin/index'),
  PlugInList: () => import('@/views/Plugin/list'),
  PlugInCreate: () => import('@/views/Plugin/create'),
  PlugInEdit: () => import('@/views/Plugin/edit')
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
    // console.log('item.component',item.component)
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
        id: 90,
        pid: 29,
        path: 'brand',
        component: 'Brand',
        redirect: '',
        alwaysShow: false,
        name: 'Brand',
        hidden: false,
        meta: {
          title: '品牌',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 91,
            pid: 90,
            path: 'brandList',
            component: 'BrandList',
            redirect: '',
            alwaysShow: false,
            name: 'BrandList',
            hidden: false,
            meta: {
              title: '品牌列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 92,
            pid: 90,
            path: 'brandCreate',
            component: 'BrandCreate',
            redirect: '',
            alwaysShow: false,
            name: 'BrandCreate',
            hidden: true,
            meta: {
              title: '创建品牌',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 93,
            pid: 90,
            path: 'brandEdit',
            component: 'BrandEdit',
            redirect: '',
            alwaysShow: false,
            name: 'BrandEdit',
            hidden: true,
            meta: {
              title: '保存品牌',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 94,
            pid: 90,
            path: 'brandDestroy',
            component: 'BrandDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'BrandDestroy',
            hidden: true,
            meta: {
              title: '删除品牌',
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
      },
      {
        id: 105,
        pid: 29,
        path: 'specification',
        component: 'Specification',
        redirect: '',
        alwaysShow: false,
        name: 'Specification',
        hidden: false,
        meta: {
          title: '规格',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 106,
            pid: 105,
            path: 'specificationList',
            component: 'SpecificationList',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationList',
            hidden: false,
            meta: {
              title: '规格列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 107,
            pid: 105,
            path: 'specificationCreate',
            component: 'SpecificationCreate',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationCreate',
            hidden: true,
            meta: {
              title: '创建规格',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 108,
            pid: 105,
            path: 'specificationEdit',
            component: 'SpecificationEdit',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationEdit',
            hidden: true,
            meta: {
              title: '保存规格',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 109,
            pid: 105,
            path: 'specificationDestroy',
            component: 'SpecificationDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationDestroy',
            hidden: true,
            meta: {
              title: '删除规格',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 110,
        pid: 29,
        path: 'specificationGroup',
        component: 'SpecificationGroup',
        redirect: '',
        alwaysShow: false,
        name: 'SpecificationGroup',
        hidden: false,
        meta: {
          title: '规格组',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 111,
            pid: 110,
            path: 'specificationGroupList',
            component: 'SpecificationGroupList',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationGroupList',
            hidden: false,
            meta: {
              title: '规格组列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 112,
            pid: 110,
            path: 'specificationGroupCreate',
            component: 'SpecificationGroupCreate',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationGroupCreate',
            hidden: true,
            meta: {
              title: '创建规格组',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 113,
            pid: 110,
            path: 'specificationGroupEdit',
            component: 'SpecificationGroupEdit',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationGroupEdit',
            hidden: true,
            meta: {
              title: '保存规格组',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 114,
            pid: 110,
            path: 'specificationGroupDestroy',
            component: 'SpecificationGroupDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'SpecificationGroupDestroy',
            hidden: true,
            meta: {
              title: '删除规格组',
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
    id: 121,
    pid: 0,
    path: '/logisticManagement',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'LogisticManagement',
    hidden: false,
    meta: {
      title: '物流管理',
      icon: 'logistics',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 122,
        pid: 121,
        path: 'freightList',
        component: 'FreightList',
        redirect: '',
        alwaysShow: false,
        name: 'FreightList',
        hidden: false,
        meta: {
          title: '运费模板',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 123,
        pid: 121,
        path: 'freightCreate',
        component: 'FreightCreate',
        redirect: '',
        alwaysShow: false,
        name: 'FreightCreate',
        hidden: true,
        meta: {
          title: '创建运费模板',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 124,
        pid: 121,
        path: 'freightEdit',
        component: 'FreightEdit',
        redirect: '',
        alwaysShow: false,
        name: 'FreightEdit',
        hidden: true,
        meta: {
          title: '保存运费模板',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 125,
        pid: 121,
        path: 'freightDestroy',
        component: 'FreightDestroy',
        redirect: '',
        alwaysShow: false,
        name: 'FreightDestroy',
        hidden: true,
        meta: {
          title: '删除运费模板',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 129,
        pid: 121,
        path: 'dhlList',
        component: 'DhlList',
        redirect: '',
        alwaysShow: false,
        name: 'DhlList',
        hidden: false,
        meta: {
          title: '快递公司',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 130,
        pid: 121,
        path: 'dhlCreate',
        component: 'DhlCreate',
        redirect: '',
        alwaysShow: false,
        name: 'DhlCreate',
        hidden: true,
        meta: {
          title: '创建快递公司',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 131,
        pid: 121,
        path: 'dhlEdit',
        component: 'DhlEdit',
        redirect: '',
        alwaysShow: false,
        name: 'DhlEdit',
        hidden: true,
        meta: {
          title: '保存快递公司',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 132,
        pid: 121,
        path: 'dhlDestroy',
        component: 'DhlDestroy',
        redirect: '',
        alwaysShow: false,
        name: 'DhlDestroy',
        hidden: true,
        meta: {
          title: '删除快递公司',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
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
    id: 149,
    pid: 0,
    path: '/statistics',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'Statistics',
    hidden: false,
    meta: {
      title: '统计',
      icon: 'statistics',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 150,
        pid: 149,
        path: 'statisticsVisit',
        component: 'StatisticsVisit',
        redirect: '',
        alwaysShow: false,
        name: 'StatisticsVisit',
        hidden: false,
        meta: {
          title: '使用分析',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 151,
        pid: 149,
        path: 'statisticsPay',
        component: 'StatisticsPay',
        redirect: '',
        alwaysShow: false,
        name: 'StatisticsPay',
        hidden: false,
        meta: {
          title: '交易分析',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 152,
        pid: 149,
        path: 'statisticsAgeAndSex',
        component: 'StatisticsAgeAndSex',
        redirect: '',
        alwaysShow: false,
        name: 'StatisticsAgeAndSex',
        hidden: false,
        meta: {
          title: '用户画像',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      }
    ]
  },
  {
    id: 170,
    pid: 0,
    path: '/articleManage',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'ArticleManage',
    hidden: false,
    meta: {
      title: '文章管理',
      icon: 'article',
      roles: ['admin'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 171,
        pid: 170,
        path: 'column',
        component: 'Column',
        redirect: '',
        alwaysShow: false,
        name: 'Column',
        hidden: false,
        meta: {
          title: '栏目',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 172,
            pid: 171,
            path: 'columnList',
            component: 'ColumnList',
            redirect: '',
            alwaysShow: false,
            name: 'ColumnList',
            hidden: false,
            meta: {
              title: '栏目列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 173,
            pid: 171,
            path: 'columnCreate',
            component: 'ColumnCreate',
            redirect: '',
            alwaysShow: false,
            name: 'ColumnCreate',
            hidden: true,
            meta: {
              title: '创建栏目',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 174,
            pid: 171,
            path: 'columnEdit',
            component: 'ColumnEdit',
            redirect: '',
            alwaysShow: false,
            name: 'ColumnEdit',
            hidden: true,
            meta: {
              title: '保存栏目',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 175,
            pid: 171,
            path: 'columnDestroy',
            component: 'ColumnDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'ColumnDestroy',
            hidden: true,
            meta: {
              title: '删除栏目',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 176,
        pid: 170,
        path: 'article',
        component: 'Article',
        redirect: '',
        alwaysShow: false,
        name: 'Article',
        hidden: false,
        meta: {
          title: '文章',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 177,
            pid: 176,
            path: 'articleList',
            component: 'ArticleList',
            redirect: '',
            alwaysShow: false,
            name: 'ArticleList',
            hidden: false,
            meta: {
              title: '文章列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 178,
            pid: 176,
            path: 'articleCreate',
            component: 'ArticleCreate',
            redirect: '',
            alwaysShow: false,
            name: 'ArticleCreate',
            hidden: true,
            meta: {
              title: '创建文章',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 179,
            pid: 176,
            path: 'articleEdit',
            component: 'ArticleEdit',
            redirect: '',
            alwaysShow: false,
            name: 'ArticleEdit',
            hidden: true,
            meta: {
              title: '保存文章',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 180,
            pid: 176,
            path: 'articleDestroy',
            component: 'ArticleDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'ArticleDestroy',
            hidden: true,
            meta: {
              title: '删除文章',
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
  },
  {
    id: 23,
    pid: 0,
    path: '/toolManagement',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'ToolManagement',
    hidden: false,
    meta: {
      title: '工具',
      icon: 'tool',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 60,
        pid: 23,
        path: 'redisService',
        component: 'RedisService',
        redirect: '',
        alwaysShow: false,
        name: 'RedisService',
        hidden: false,
        meta: {
          title: 'Redis管理',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 61,
            pid: 60,
            path: 'redisServiceList',
            component: 'RedisServiceList',
            redirect: '',
            alwaysShow: false,
            name: 'RedisServiceList',
            hidden: false,
            meta: {
              title: 'Redis列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 62,
            pid: 60,
            path: 'redisServiceDestroy',
            component: 'RedisServiceDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'RedisServiceDestroy',
            hidden: true,
            meta: {
              title: '删除Redis',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 63,
            pid: 60,
            path: 'redisPanel',
            component: 'RedisPanel',
            redirect: '',
            alwaysShow: false,
            name: 'RedisPanel',
            hidden: false,
            meta: {
              title: 'Redis面板',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 197,
            pid: 60,
            path: 'redisServiceDetail',
            component: 'RedisServiceDetail',
            redirect: '',
            alwaysShow: false,
            name: 'RedisServiceDetail',
            hidden: true,
            meta: {
              title: 'Redis详情',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 100,
        pid: 23,
        path: 'resource',
        component: 'Resource',
        redirect: '',
        alwaysShow: false,
        name: 'Resource',
        hidden: false,
        meta: {
          title: '资源',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 101,
            pid: 100,
            path: 'resourceList',
            component: 'ResourceList',
            redirect: '',
            alwaysShow: false,
            name: 'ResourceList',
            hidden: false,
            meta: {
              title: '资源列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 104,
            pid: 100,
            path: 'resourceDestroy',
            component: 'ResourceDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'ResourceDestroy',
            hidden: true,
            meta: {
              title: '删除资源',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 142,
        pid: 23,
        path: 'banner',
        component: 'Banner',
        redirect: '',
        alwaysShow: false,
        name: 'Banner',
        hidden: false,
        meta: {
          title: '轮播管理',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 143,
            pid: 142,
            path: 'bannerList',
            component: 'BannerList',
            redirect: '',
            alwaysShow: false,
            name: 'BannerList',
            hidden: false,
            meta: {
              title: '轮播列表',
              icon: '',
              roles: ['admin', 'edits'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 144,
            pid: 142,
            path: 'bannerCreate',
            component: 'BannerCreate',
            redirect: '',
            alwaysShow: false,
            name: 'BannerCreate',
            hidden: true,
            meta: {
              title: '创建轮播',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 145,
            pid: 142,
            path: 'bannerEdit',
            component: 'BannerEdit',
            redirect: '',
            alwaysShow: false,
            name: 'BannerEdit',
            hidden: true,
            meta: {
              title: '保存轮播',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 147,
            pid: 142,
            path: 'bannerDestroy',
            component: 'BannerDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'BannerDestroy',
            hidden: true,
            meta: {
              title: '删除轮播',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 160,
        pid: 23,
        path: 'coupon',
        component: 'Coupon',
        redirect: '',
        alwaysShow: false,
        name: 'Coupon',
        hidden: false,
        meta: {
          title: '优惠券',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 161,
            pid: 160,
            path: 'couponList',
            component: 'CouponList',
            redirect: '',
            alwaysShow: false,
            name: 'CouponList',
            hidden: false,
            meta: {
              title: '优惠券列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 162,
            pid: 160,
            path: 'couponCreate',
            component: 'CouponCreate',
            redirect: '',
            alwaysShow: false,
            name: 'CouponCreate',
            hidden: true,
            meta: {
              title: '创建优惠券',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 163,
            pid: 160,
            path: 'couponEdit',
            component: 'CouponEdit',
            redirect: '',
            alwaysShow: false,
            name: 'CouponEdit',
            hidden: true,
            meta: {
              title: '优惠券操作',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 164,
            pid: 160,
            path: 'couponDestroy',
            component: 'CouponDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'CouponDestroy',
            hidden: true,
            meta: {
              title: '删除优惠券',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 165,
        pid: 23,
        path: 'comment',
        component: 'Comment',
        redirect: '',
        alwaysShow: false,
        name: 'Comment',
        hidden: false,
        meta: {
          title: '评价',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 166,
            pid: 165,
            path: 'commentList',
            component: 'CommentList',
            redirect: '',
            alwaysShow: false,
            name: 'CommentList',
            hidden: false,
            meta: {
              title: '评价列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 167,
            pid: 165,
            path: 'commentCreate',
            component: 'CommentCreate',
            redirect: '',
            alwaysShow: false,
            name: 'CommentCreate',
            hidden: true,
            meta: {
              title: '评价回复',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 168,
            pid: 165,
            path: 'commentEdit',
            component: 'CommentEdit',
            redirect: '',
            alwaysShow: false,
            name: 'CommentEdit',
            hidden: true,
            meta: {
              title: '评价操作',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 169,
            pid: 165,
            path: 'commentDestroy',
            component: 'CommentDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'CommentDestroy',
            hidden: true,
            meta: {
              title: '删除评价',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          }
        ]
      },
      {
        id: 181,
        pid: 23,
        path: 'distribution',
        component: 'Distribution',
        redirect: '',
        alwaysShow: false,
        name: 'Distribution',
        hidden: false,
        meta: {
          title: '分销',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        },
        children: [
          {
            id: 182,
            pid: 181,
            path: 'distributionList',
            component: 'DistributionList',
            redirect: '',
            alwaysShow: false,
            name: 'DistributionList',
            hidden: false,
            meta: {
              title: '分销列表',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 183,
            pid: 181,
            path: 'distributionCreate',
            component: 'DistributionCreate',
            redirect: '',
            alwaysShow: false,
            name: 'DistributionCreate',
            hidden: true,
            meta: {
              title: '创建分销',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 184,
            pid: 181,
            path: 'distributionEdit',
            component: 'DistributionEdit',
            redirect: '',
            alwaysShow: false,
            name: 'DistributionEdit',
            hidden: true,
            meta: {
              title: '保存分销',
              icon: '',
              roles: ['admin'],
              noCache: false,
              breadcrumb: true
            }
          },
          {
            id: 185,
            pid: 181,
            path: 'distributionDestroy',
            component: 'DistributionDestroy',
            redirect: '',
            alwaysShow: false,
            name: 'DistributionDestroy',
            hidden: true,
            meta: {
              title: '删除分销',
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
    id: 156,
    pid: 0,
    path: '/plugIn',
    component: 'Layout',
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'PlugIn',
    hidden: false,
    meta: {
      title: '插件',
      icon: 'plug-in',
      roles: ['admin', 'edits'],
      noCache: false,
      breadcrumb: true
    },
    children: [
      {
        id: 157,
        pid: 156,
        path: 'plugInList',
        component: 'PlugInList',
        redirect: '',
        alwaysShow: false,
        name: 'PlugInList',
        hidden: false,
        meta: {
          title: '插件列表',
          icon: '',
          roles: ['admin', 'edits'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 159,
        pid: 156,
        path: 'plugInCreate',
        component: 'PlugInCreate',
        redirect: '',
        alwaysShow: false,
        name: 'PlugInCreate',
        hidden: true,
        meta: {
          title: '插件安装',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 192,
        pid: 156,
        path: 'plugInDestroy',
        component: 'PlugInDestroy',
        redirect: '',
        alwaysShow: false,
        name: 'PlugInDestroy',
        hidden: true,
        meta: {
          title: '插件卸载',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      },
      {
        id: 193,
        pid: 156,
        path: 'plugInEdit',
        component: 'PlugInEdit',
        redirect: '',
        alwaysShow: false,
        name: 'PlugInEdit',
        hidden: true,
        meta: {
          title: '插件更新',
          icon: '',
          roles: ['admin'],
          noCache: false,
          breadcrumb: true
        }
      }
    ]
  }
]
