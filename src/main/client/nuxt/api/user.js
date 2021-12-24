import request from "@/plugins/request";
import Qs from "qs";

const BASE_URL = "user/";

export function detail() {
  return request({
    url: "user",
    method: "GET"
  });
}
export function edit(data) {
  data = Qs.parse(data);
  return request({
    url: "user",
    method: "POST",
    data
  });
}
export function cancel(data) {
  data = Qs.parse(data);
  return request({
    url: "cancel",
    method: "POST",
    data
  });
}
export function getMyData(headers) {
  return headers
    ? request({
        url: BASE_URL + "myData",
        method: "GET",
        headers
      })
    : request({
        url: BASE_URL + "myData",
        method: "GET"
      });
}
