import { getList as getGoodList, goodCategory, getRecommendation } from "@/api/good";
import { getList as bannerList } from "@/api/banner";
import Good from "../components/good.vue";
export default {
  components: {
    Good
  },
  data() {
    return {
      categoryStyle: 0,
      naveOn: null,
      goodList: [],
      banner: "",
      bannerList: [],
      categoryList: [],
      categorySublevel: [],
      recommendCategoryList: [],
      recommendGoodList: []
    };
  },
  async asyncData(ctx) {
    try {
      let [
        goodData,
        categoryData,
        // recommendCategoryData = []
      ] = await Promise.all([
        getRecommendation(),
        goodCategory(),
        // goodCategory({
        //   is_recommend: 1
        // })
      ]);
      return {
        goodList: goodData.commodities,
        // bannerList: bannerData.data,
        categoryList: categoryData.types,
        // recommendCategoryList: recommendCategoryData
      };
    } catch (err) {
      ctx.$errorHandler(err);
    }
  },
  mounted() {
    this.categoryGood();
    // this.getBanner();
  },
  methods: {
    // 分类切换
    naveCut(index) {
      if (index !== -1) {
        this.naveOn = index;
        if (this.categoryList[index].children) {
          //存在子类目
          if (this.categoryList[index].children[0].resources) {
            this.categorySublevel = this.categoryList[index].children;
            this.categoryStyle = 2;
          } else {
            //存在三级
            this.categorySublevel = this.categoryList[index].children;
            this.categoryStyle = 1;
          }
        } else {
          this.categorySublevel = [];
        }
      }
    },
    // 获取分类商品
    categoryGood() {
      this.categoryList.forEach((item, index) => {
        this.recommendGoodList[index] = [];
        getGoodList({
          pageNow: 1,
          pageSize: 20,
          keyword: '',
          searchType: item.name,
          price: {
            min: 0,
            max: 0
          },
          goodId: ''
        }).then(response => {
          this.recommendGoodList[index] = response.commodities
          this.$forceUpdate();
        });
      });
    },
    // 分类移出
    naveShiftOut() {
      this.naveOn = null;
      this.categoryStyle = 0;
    },
    // 首页广告
    getBanner() {
      bannerList({
        limit: 1,
        type: 1,
        sort: "+sort"
      }).then(response => {
        this.banner = response.data[0];
      });
    }
  }
};
