import request from "@/plugins/request";
import Qs from "qs";
export function getList() {
  return request({
    url: "user/order",
    method: "GET"
  });
}
export function detail(orderId) {
  const data = Qs.stringify({
    orderId
  });
  return request({
    url: "user/order/detail",
    method: "POST",
    data
  });
}
export function quantity() {
  return request({
    url: "goodIndent/quantity",
    method: "GET"
  });
}
export function pay(id) {
  return request({
    url: "goodIndent/pay/" + id,
    method: "GET"
  });
}
export function create(data) {
  return request({
    url: "user/order",
    method: "POST",
    data
  });
}
export function addShoppingCart(data) {
  data = Qs.stringify(data);
  return request({
    url: "user/cart",
    method: "POST",
    data
  });
}
export function updateCart(data) {
  // data = Qs.stringify(data)
  return request({
    url: "user/cart",
    method: "PUT",
    data
  });
}
export function getCartList() {
  return request({
    url: "user/cart",
    method: "GET"
  });
}
export function cancel(id) {
  const data = Qs.stringify({
    orderId: id,
    status: "退款中"
  });
  return request({
    url: "user/order/update",
    method: "POST",
    data
  });
}
export function destroy(id) {
  return request({
    url: "goodIndent/destroy/" + id,
    method: "POST"
  });
}
export function receipt(id) {
  return request({
    url: "goodIndent/receipt/" + id,
    method: "POST"
  });
}
