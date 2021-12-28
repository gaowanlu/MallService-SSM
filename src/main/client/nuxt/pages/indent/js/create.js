import addressList from "@/components/Address/list";
import { create, updateCart } from "@/api/goodIndent";
export default {
  components: {
    addressList
  },
  layout: "cart",
  middleware: "auth",
  head() {
    return {
      title: "确认订单" + "-" + process.env.APP_NAME
    };
  },
  data() {
    const validateRemark = (rule, value, callback) => {
      const flag = new RegExp(
        "[`~!@#$^&*()=|{}':'\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘：”“'。？ ]"
      );
      if (flag.test(value)) {
        return callback(new Error("不允许输入非法字符"));
      } else {
        callback();
      }
    };
    return {
      loading: true,
      buttonLoading: true,
      total: 0,
      ruleForm: {
        goods: [],
        addressId: "",
        mark: ""
      },
      rules: {
        mark: [{ validator: validateRemark, trigger: "blur" }]
      }
    };
  },
  mounted() {
    $nuxt.$store.commit("setCartTitle", "确认订单");
    this.getList();
  },
  methods: {
    async getList() {
      this.ruleForm.goods = Object.values(
        this.store.get(process.env.CACHE_PR + "OrderList")
      );
      this.ruleForm.goods.forEach(item => {
        this.total += item.price * item.number;
      });
      this.buttonLoading = false;
      console.log({ ruleForm: this.ruleForm });
    },
    // 选择的地址
    selectedAddress(res) {
      this.ruleForm.addressId = res.addressId;
    },
    // 提交订单
    submit() {
      if (!this.ruleForm.addressId) {
        this.$message({
          message: "请选择地址",
          type: "error"
        });
        return false;
      }
      this.buttonLoading = true;
      create({
        mark: this.ruleForm.mark,
        addressId: this.ruleForm.addressId,
        goods: this.ruleForm.goods.map(item => ({
          goodId: item.goodId,
          num: item.number
        }))
      })
        .then(response => {
          this.buttonLoading = false;
          this.store.remove(process.env.CACHE_PR + "CartList");
          this.store.remove(process.env.CACHE_PR + "OrderList");
          updateCart([]);
          $nuxt.$router.replace({
            path: "/money/pay",
            query: { id: response.message }
          });
        })
        .catch(() => {
          this.buttonLoading = false;
        });
    },
    go() {
      $nuxt.$router.go(-1);
    }
  }
};
