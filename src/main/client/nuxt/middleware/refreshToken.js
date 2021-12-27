import Cookies from "js-cookie";
import { getMyData } from "../api/user";

export default function({ route, store, req, env }) {
  const stores = require("store");
  function getCookiesInServer(req) {
    if (req && req.headers.cookie) {
      return req.headers.cookie;
    }
  }
  let token = null;
  if (process.server) {
    token = getCookiesInServer(req);
  } else {
    token = Cookies.get("JSESSIONID");
  }

  if (token && !store.state.hasLogin) {
      store.commit('login')
  }

  // 排除登录页，不然登录无法重定向到原操作页
  const excludePath = ["/pass/login"];
  if (excludePath.indexOf(route.path) === -1) {
    stores.remove("route");
  }
}
