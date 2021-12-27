import request from "@/plugins/request";

const BASE_URL = "commodity/";

export function getRecommendation() {
  return request({
    url: BASE_URL + "recommendation",
    method: "GET"
  });
}

export function getList(data) {
  return request({
    url: "search/commodity",
    method: "POST",
    data
  });
}
export function detail(id) {
  const data = {
    pageNow: 0,
    pageSize: 0,
    keyword: "",
    searchType: "",
    price: {
      min: 0,
      max: 0
    },
    goodId: id
  };
  return request({
    url: "search/commodity",
    method: "POST",
    data
  });
}
export function goodCategory() {
  return request({
    url: BASE_URL + "typeList",
    method: "GET"
  });
}
