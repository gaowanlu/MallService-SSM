import request from '@/plugins/request'
import Qs from 'qs'
export function getList() {
  return request({
    url: 'user/addresses',
    method: 'GET',
  })
}
export function create(data) {
  data = Qs.stringify(data)
  return request({
    url: 'user/addresses',
    method: 'POST',
    data
  })
}
export function edit(data) {
  data = Qs.parse(data)
  return request({
    url: 'shipping/' + data.id,
    method: 'POST',
    data
  })
}
export function destroy(id) {
  return request({
    url: 'shipping/destroy/' + id,
    method: 'POST'
  })
}
export function defaultSet(data) {
  data = Qs.parse(data)
  return request({
    url: 'shipping/default/set',
    method: 'POST',
    data
  })
}
