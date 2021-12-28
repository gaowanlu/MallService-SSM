import request from '@/plugins/request'
import Qs from 'qs'
export function unifiedPayment(data) {
  data = Qs.parse(data)
  return request({
    url: 'unifiedPayment',
    method: 'POST',
    data
  })
}
export function balancePay(orderId) {
  const data = Qs.stringify({
    orderId,
    status: '待发货'
  })
  return request({
    url: 'user/order/update',
    method: 'PUT',
    data
  })
}
