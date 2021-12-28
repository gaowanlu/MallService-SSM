import {goodCategory as getGoodCategory} from '@/api/good'
export default {
  data() {
    return {
      goodCategory: []
    }
  },
  async asyncData (ctx) {
    try {
      let [ goodCategoryData ] = await Promise.all([
        getGoodCategory({tree: true})
      ]);
      return {
        goodCategory: goodCategoryData.types
      }
    } catch(err) {
      ctx.$errorHandler(err)
    }
  },
  head () {
    return {
      title: '全部商品分类-' + process.env.APP_NAME
    }
  },
  mounted() {

  },
  methods: {

  }
}
