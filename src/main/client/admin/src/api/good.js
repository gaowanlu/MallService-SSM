import request from '@/utils/request'
import Qs from 'qs'
export function getList(query) {
  return request({
    url: 'search/commodity',
    method: 'post',
    data: query
  })
}

export function create(data) {
  return request({
    url: 'admin/commodity/add',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data
  })
}

export function edit(data) {
  return request({
    url: 'admin/commodity/update',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data
  })
}

export function destroy(id) {
  const data = Qs.stringify({
    goodId: id
  })
  return request({
    url: 'admin/commodity/delete',
    method: 'POST',
    data
  })
}

export function detail(id) {
  return request({
    url: 'good/' + id,
    method: 'GET'
  })
}

export function state(data) {
  return request({
    url: 'admin/commodity/update',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data
  })
}

export function specification(id) {
  return request({
    url: 'good/specification/' + id,
    method: 'GET'
  })
}
