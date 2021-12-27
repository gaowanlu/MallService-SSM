import { login } from "@/api/login";
import { getMyData } from "@/api/user";
import { mapMutations } from "vuex";
export default {
  layout: "login",
  head() {
    return {
      title: "登录" + "-" + process.env.APP_NAME
    };
  },
  data() {
    const validateCellphone = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入手机号"));
      } else {
        const myreg = /^1[3456789]\d{9}$/;
        if (!myreg.test(value)) {
          callback(new Error("手机号格式有误"));
        }
        callback();
      }
    };
    return {
      method: 1,
      ruleForm: {
        email: "moezrf@gmail.com",
        password: "123456",
        remember: false
      },
      loading: false,
      rules: {
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱",
            trigger: ["blur"]
          }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, message: "密码长度必须大于5位", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    ...mapMutations(["login"]),
    toLogin() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.loading = true;
          const requestInfo = {
            id: this.ruleForm.email,
            password: this.ruleForm.password,
            remember: this.ruleForm.remember
          };
          login(requestInfo)
            .then(async response => {
              const { result } = response;
              if (result) {
                const myData = await getMyData();
                this.login(myData);
                this.$message({
                  message: "登录成功",
                  type: "success"
                });
                this.loading = false;
                const route = this.store.get("route");
                if (route) {
                  this.store.remove("route");
                  this.$router.replace({
                    path: route.path,
                    query: route.query
                  });
                } else {
                  $nuxt.$router.replace("/user/portal");
                }
              }
            })
            .catch(() => {
              this.loading = false;
            });
        }
      });
    }
  }
};
