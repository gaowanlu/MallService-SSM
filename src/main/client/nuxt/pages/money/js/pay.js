import { detail } from "@/api/goodIndent";
import { balancePay } from "@/api/pay";
import VueCountdown from "@chenfengyuan/vue-countdown";
import VueQrcode from "@chenfengyuan/vue-qrcode";
export default {
  components: { VueCountdown, VueQrcode },
  layout: "cart",
  middleware: "auth",
  head() {
    return {
      title: "支付订单" + "-" + process.env.APP_NAME
    };
  },
  data() {
    return {
      loading: true,
      detail: false,
      centerDialogVisible: false,
      buttonLoading: false,
      qrcode: "",
      timer: null,
      list: {},
      user: {}
    };
  },
  mounted() {
    $nuxt.$store.commit("setCartTitle", "支付订单");
    this.getList();
    this.userInfo();
  },
  methods: {
    getList() {
      detail($nuxt.$route.query.id).then(response => {
        this.list = response;
        this.loading = false;
      });
    },
    userInfo() {
      if ($nuxt.$store.state.hasLogin) {
        this.user = $nuxt.store.get(process.env.CACHE_PR + "UserInfo");
      }
    },
    // 显示详情
    showDetail() {
      this.detail = !this.detail;
    },
    // 支付
    payment(type) {
      this.buttonLoading = true;
      if (type === 1) {
        // 余额支付
        balancePay($nuxt.$route.query.id).then(response => {
          console.log(response)
          this.buttonLoading = false;
          $nuxt.$router.replace("/money/success");
        });
      }
    }
  },
  destroyed() {
    clearInterval(this.timer);
  }
};
