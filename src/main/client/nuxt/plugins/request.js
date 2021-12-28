import axios from "axios";
import { Message, MessageBox } from "element-ui";
import store from "@/store";
import { getToken } from "@/plugins/auth";
const service = axios.create({
  baseURL: '/api',
  timeout: 50000,
  proxy: process.env.NODE_ENV === 'development' ? {
    host: 'localhost',
    port: 8080,
    protocol: 'http'
  } : undefined
});
service.interceptors.request.use(
  config => {
    config.headers["Accept"] = "application/json";
    return config;
  },
  error => {
    Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  response => {
    if (response.data.result === "error") {
      Message({
        message: "服务器返回格式有误",
        type: "error",
        duration: 5 * 1000
      });
    }
    const res = response.data;
    if (response.status !== 200) {
      Message({
        message: res.message || "Error",
        type: "error",
        duration: 5 * 1000
      });
      return Promise.reject(new Error(res.message || "Error"));
    } else {
      return res;
    }
  },
  error => {
    // console.log('err' + error) // for debug
    // console.log("error.response.data", error.response.data);

    console.log({error})

    if (
      error.response.status === 403 && !error.response.data.result
    ) {
      $nuxt.$store.commit("logout");
      // location.reload();
    } else {
      Message({
        message: error.response.data.message || '未知错误',
        type: "error",
        duration: 5 * 1000
      });
    }
    return Promise.reject(error);
  }
);

export default service;
