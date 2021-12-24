import request from '@/utils/request'
import Qs from 'qs'
export function getList() {
  return request({
    url: 'commodity/typeList',
    method: 'GET'
  })
}

export function create(data) {
  data = Qs.parse(data)
  return request({
    url: 'category',
    method: 'POST',
    data
  })
}

export function edit(data) {
  data = Qs.parse(data)
  return request({
    url: 'category/' + data.id,
    method: 'POST',
    data
  })
}

export function destroy(id) {
  return request({
    url: 'category/destroy/' + id,
    method: 'POST'
  })
}
