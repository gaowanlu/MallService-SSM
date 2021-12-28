import { getList as getGoodList } from "@/api/good";
export default {
  data() {
    return {
      goodList: [],
      listQuery: {},
      loading: false,
      total: 0,
      title: ""
    };
  },
  async asyncData(ctx) {
    try {
      const { query } = ctx;
      const listQuery = {
        pageNow: 1,
        pageSize: 10,
        keyword: query.keyword ? query.keyword : '',
        searchType: query.name ? query.name : '',
        price: {
          min: 0,
          max: 0
        },
        goodId: ""
      };
      let [goodData] = await Promise.all([getGoodList(listQuery)]);
      return {
        goodList: goodData.commodities,
        total: goodData.commodities.length,
        listQuery: listQuery,
        title: query.name ? query.name : "全部商品"
      };
    } catch (err) {
      ctx.$errorHandler(err);
    }
  },
  head() {
    return {
      title:
        this.title +
        (this.listQuery.pid ? "-商品分类-" : "-搜索结果-") +
        process.env.APP_NAME
    };
  },
  methods: {
    getList() {
      this.loading = true;
      Promise.all([getGoodList(this.listQuery)])
        .then(([goodData]) => {
          this.goodList = goodData.data;
          this.total = goodData.total;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
        });
    },
    //筛选点击
    tabClick(index) {
      if (index) {
        if (index === "sales") {
          this.listQuery.sort = "-sales";
        } else {
          if (this.listQuery.sort !== "+order_price") {
            this.listQuery.sort = "+order_price";
          } else {
            this.listQuery.sort = "-order_price";
          }
        }
      } else {
        this.listQuery.sort = "";
      }
      this.listQuery.page = 1;
      this.getList();
    },
    handleSizeChange(val) {
      this.listQuery.limit = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.listQuery.page = val;
      this.getList();
    }
  }
};
