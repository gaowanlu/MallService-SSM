import Cookies from "js-cookie";

export default function({ route, redirect, store, req, env }) {
  const stores = require("store");

  if (!store.state.hasLogin) {
    stores.set("route", { path: route.path, query: route.query });
    // 删除本地缓存
    store.commit("logout");
    return redirect("/pass/login");
  } else {
    store.commit("login");
  }
}
