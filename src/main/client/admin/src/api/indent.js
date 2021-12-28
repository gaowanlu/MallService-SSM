import request from '@/utils/request'
import Qs from 'qs'
export function getList(query) {
  return request({
    url: 'admin/order/search',
    method: 'POST',
    data: query
  })
}

export function detail(id) {
  const data = {
    'orderId': id,
    'email': '',
    'status': '',
    'time': {
      'max': '2099-12-31 23:59:59',
      'min': '1970-01-01 00:00:00'
    },
    'pageSize': 20,
    'pageNow': 1
  }
  return request({
    url: 'admin/order/search',
    method: 'POST',
    data
  })
}

export function shipment(data) {
  data = Qs.parse(data)
  return request({
    url: 'indent/shipment',
    method: 'POST',
    data
  })
}

export function dhl(data) {
  data = Qs.parse(data)
  return request({
    url: 'indent/dhl',
    method: 'POST',
    data
  })
}

export function query(id) {
  return request({
    url: 'indent/query/' + id,
    method: 'GET'
  })
}

export function refund(id, data) {
  data = Qs.parse(data)
  return request({
    url: 'indent/refund/' + id,
    method: 'POST',
    data
  })
}

export function receiving(data) {
  data = Qs.parse(data)
  return request({
    url: 'indent/receiving',
    method: 'POST',
    data
  })
}
