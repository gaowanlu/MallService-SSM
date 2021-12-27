import request from "@/plugins/request";
import Qs from "qs";

const BASE_URL = "identitySecurity/";

export function login(data) {
  data = Qs.stringify(data);
  return request({
    url: BASE_URL + "login",
    method: "POST",
    data
  });
}
export function refreshToken(data) {
  data = Qs.parse(data);
  return request({
    url: "refreshToken",
    method: "POST",
    data
  });
}
export function cellphoneCode(data) {
  data = Qs.parse(data);
  return request({
    url: "cellphoneCode",
    method: "POST",
    data
  });
}
export function emailCode(data) {
  data = Qs.stringify(data);
  return request({
    url: BASE_URL + "sendEmailCode",
    method: "POST",
    data
  });
}
export function verifyEmail(data) {
  data = Qs.parse(data);
  return request({
    url: "verifyEmail",
    method: "POST",
    data
  });
}
export function findPassword(data) {
  data = Qs.parse(data);
  return request({
    url: "findPassword",
    method: "POST",
    data
  });
}
export function changeCellphone(data) {
  data = Qs.parse(data);
  return request({
    url: "changeCellphone",
    method: "POST",
    data
  });
}
export function amendPassword(data) {
  data = Qs.parse(data);
  return request({
    url: "amendPassword",
    method: "POST",
    data
  });
}
export function register(data) {
  data = Qs.stringify(data);
  return request({
    url: BASE_URL + "register",
    method: "POST",
    data
  });
}
