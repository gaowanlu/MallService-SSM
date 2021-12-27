import request from '@/utils/request'
import Qs from 'qs'
export function loginByEmail(data) {
  data = Qs.stringify(data)
  return request({
    url: 'identitySecurity/login',
    method: 'POST',
    data
  })
}
export function loginByUsername(data) {
  data = Qs.parse(data)
  return request({
    url: 'login',
    method: 'POST',
    data
  })
}

export function refreshToken(data) {
  data = Qs.parse(data)
  return request({
    url: 'refreshToken',
    method: 'POST',
    data
  })
}

export function getUserInfo() {
  return request({
    url: '/user/myData',
    method: 'GET'
  })
}

