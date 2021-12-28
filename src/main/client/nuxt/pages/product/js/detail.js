const store = require("store");
import { detail } from "@/api/good";
import { updateCart } from "@/api/goodIndent";
import {
  create as collectCreate,
  destroy as collectDestroy,
  detail as getCollectDetail
} from "@/api/collect";
import sku from "@/components/Sku";
import VueVideo from "@/components/VueVideo";
import "video.js/dist/video-js.css";

export default {
  components: {
    sku,
    VueVideo
  },
  data() {
    return {
      tab: 1,
      tabLoading: false,
      goodDetail: {},
      specificationDefaultDisplay: {},
      resources_many: [],
      resources_many_img: [],
      collect: 0,
      poster: "",
      cartGood: {
        number: 1
      }
    };
  },
  async asyncData(ctx) {
    try {
      const { query } = ctx;
      let [goodDetailData] = await Promise.all([detail(query.id)]);
      const goodDetail = goodDetailData.commodities[0];
      goodDetail.good_sku = [
        {
          good_id: goodDetail.goodId,
          price: goodDetail.price,
          inventory: goodDetail.stock,
          product_sku: goodDetail.good_sku
        }
      ];
      let resources_many_img = [];
      if (goodDetail.imgsURL.length > 0) {
        goodDetail.imgsURL.forEach((item, index) => {
          resources_many_img.push(item);
        });
      }
      return {
        goodDetail,
        resources_many_img: resources_many_img
      };
    } catch (err) {
      ctx.$errorHandler(err);
    }
  },
  head() {
    return {
      title: this.goodDetail.name + "-" + process.env.APP_NAME,
      meta: [
        {
          hid: "index",
          name: this.goodDetail.name + "-" + process.env.APP_NAME,
          content: this.goodDetail.keywords
            ? this.goodDetail.keywords
            : process.env.APP_KEYWORD
        },
        {
          hid: "description",
          name: "description",
          content: this.goodDetail.short_description
            ? this.goodDetail.short_description
            : process.env.APP_DESCRIPTION
        },
        {
          hid: "keywords",
          name: "keywords",
          content: this.goodDetail.keywords
            ? this.goodDetail.keywords
            : process.env.APP_KEYWORD
        }
      ]
    };
  },
  mounted() {
    if ($nuxt.$store.state.hasLogin) {
      // this.getCollect()
    }
  },
  methods: {
    //选择后返回的数据
    purchasePattern(data) {
      this.specificationDefaultDisplay = data;
    },
    buy() {
      if (!$nuxt.$store.state.hasLogin) {
        $nuxt.$store.commit("loginCheck");
        return false;
      }
      const cartMap = new Map()
      cartMap.set(this.goodDetail.goodId, {
        ...this.goodDetail,
        number: this.cartGood.number,
      });
      store.set(process.env.CACHE_PR + "OrderList", [...cartMap.values()]);
      this.$router.replace("/indent/create");
    },
    getCollect() {
      getCollectDetail($nuxt.$route.query.id).then(response => {
        this.collect = response;
      });
    },
    // 收藏
    toCollect() {
      if (!$nuxt.$store.state.hasLogin) {
        $nuxt.$store.commit("loginCheck");
        return false;
      }
      if (this.collect) {
        collectDestroy(this.goodDetail.id);
      } else {
        collectCreate(this.goodDetail);
      }
      this.collect = !this.collect;
    },
    // 切换栏目
    cutTab(index) {
      this.tabLoading = true;
      this.tab = index;
      setTimeout(() => {
        this.tabLoading = false;
      }, 1000);
    },
    // 加入购物车
    addCart() {
      const cartList = store.get(process.env.CACHE_PR + "CartList") || [];
      const cartMap = new Map();
      cartList.forEach(item => cartMap.set(item.goodId, item));
      const img = this.goodDetail.imgsURL[0];
      const t = cartMap.get(this.goodDetail.goodId);
      if (t) {
        t.number += this.cartGood.number;
        if (t.number > this.goodDetail.stock) {
          t.number = this.goodDetail.stock;
        }
        t.price = +this.goodDetail.price * t.number;
        t.name = this.goodDetail.name;
        t.goodId = this.goodDetail.goodId;
        t.img = img;
      } else {
        cartMap.set(this.goodDetail.goodId, {
          price: this.goodDetail.price * this.cartGood.number,
          number: this.cartGood.number,
          name: this.goodDetail.name,
          goodId: this.goodDetail.goodId,
          img
        });
      }
      store.set(process.env.CACHE_PR + "CartList", [...cartMap.values()]);
      updateCart([...cartMap.values()].map(o => ({
        goodId: o.goodId,
        num: o.number
      }))).then(() => {
        this.$message.success("加入购物车成功");
      });
    }
  }
};
