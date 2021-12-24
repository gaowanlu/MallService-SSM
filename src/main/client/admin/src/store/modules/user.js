import { loginByUsername, getUserInfo, refreshToken } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'

const user = {
  state: {
    user: '',
    status: '',
    code: '',
    token: getToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
    setting: {
      articlePlatform: []
    },
    login: false
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_SETTING: (state, setting) => {
      state.setting = setting
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_LOGIN: (state, login) => {
      state.login = login
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({ commit }, userInfo) {
      // removeToken('access_token')
      return new Promise((resolve, reject) => {
        // 处理登录状态
        if (getToken('access_token')) {
          if ((new Date()).getTime() > getToken('expires_in')) { // token失效
            userInfo.type = 2
            userInfo.refresh_token = getToken('refresh_token')
          }
        } else { // 第一次登录
          userInfo.type = 1
        }
        loginByUsername(userInfo).then(response => {
          const token = response.data
          // 保存会token数据
          if (token) {
            let refresh_expires_in = 0
            if (userInfo.remember) {
              refresh_expires_in = token.refresh_expires_in ? token.refresh_expires_in : 0
            }
            setToken('access_token', token.access_token, refresh_expires_in)
            setToken('expires_in', (new Date()).getTime() + token.expires_in * 1000, refresh_expires_in)
            setToken('refresh_token', token.refresh_token, refresh_expires_in)
            setToken('token_type', token.token_type, refresh_expires_in)
            commit('SET_TOKEN', token.access_token)
            setToken('', token.access_token, refresh_expires_in)
          } else {
            setToken('', getToken('access_token'))
          }
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 刷新token
    RefreshToken({ commit }) {
      return new Promise((resolve, reject) => {
        refreshToken({
          refresh_token: getToken('refresh_token')
        }).then(response => {
          const token = response.data
          setToken('access_token', token.access_token)
          setToken('expires_in', (new Date()).getTime() + token.expires_in * 1000)
          setToken('refresh_token', token.refresh_token)
          setToken('token_type', token.token_type)
          commit('SET_TOKEN', token.access_token)
          setToken('', token.access_token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo().then(response => {
          if (!response.data) { // 由于mockjs 不支持自定义状态码只能这样hack
            reject('error')
          }
          const data = response.data

          resolve(response)
          commit('SET_LOGIN', true)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.profilePhotoURL)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 第三方验证登录
    // LoginByThirdparty({ commit, state }, code) {
    //   return new Promise((resolve, reject) => {
    //     commit('SET_CODE', code)
    //     loginByThirdparty(state.status, state.email, state.code).then(response => {
    //       commit('SET_TOKEN', response.data.token)
    //       setToken(response.data.token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        removeToken('access_token')
        removeToken('expires_in')
        removeToken('refresh_token')
        removeToken('token_type')
        resolve()
        // logout(state.token).then(() => {
        //   commit('SET_TOKEN', '')
        //   commit('SET_ROLES', [])
        //   removeToken()
        //   resolve()
        // }).catch(error => {
        //   reject(error)
        // })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        removeToken('access_token')
        removeToken('expires_in')
        removeToken('refresh_token')
        removeToken('token_type')
        resolve()
      })
    },

    // 动态修改权限
    ChangeRoles({ commit, dispatch }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken('', role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          dispatch('GenerateRoutes', data) // 动态修改权限后 重绘侧边菜单
          resolve()
        })
      })
    }
  }
}

export default user
