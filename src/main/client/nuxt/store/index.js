const store = require("store");
import { setToken, removeToken } from "@/plugins/auth";
import { getMyData } from "../api/user";

export const state = () => ({
  hasLogin: false,
  cartTitle: "",
  shoppingCartNumber: 0,
  searchKeyword: ""
});

export const mutations = {
  login(state, userInfo = null) {
    state.hasLogin = true;
    if (userInfo) {
      store.set(process.env.CACHE_PR + "UserInfo", userInfo);
    }
  },
  setShoppingCartNumber(state, provider) {
    state.shoppingCartNumber = provider;
  },
  setSearchKeyword(state, provider) {
    state.searchKeyword = provider;
  },
  logout(state) {
    store.remove(process.env.CACHE_PR + "ApplyToken");
    store.remove(process.env.CACHE_PR + "SessionKey");
    store.remove(process.env.CACHE_PR + "Openid");
    store.remove(process.env.CACHE_PR + "UserInfo");
    store.remove(process.env.CACHE_PR + "CartList");
    removeToken("token");
    removeToken("expires_in");
    removeToken("refresh_token");
    removeToken("token_type");
    removeToken("JSESSIONID");
    state.hasLogin = false;
    state.userInfo = {};
  },
  loginCheck(state) {
    console.log(state)
    if (!state.hasLogin) {
      store.set("route", {
        path: $nuxt.$route.path,
        query: $nuxt.$route.query
      });
      $nuxt.$router.replace("/pass/login");
      $nuxt.$message.warning("请先登录");
    }
  },
  setCartTitle(state, provider) {
    state.cartTitle = provider;
  }
};
