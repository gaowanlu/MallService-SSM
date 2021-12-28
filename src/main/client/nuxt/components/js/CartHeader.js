export default {
  data() {
    return {
      user:{},
      userActive: false,
    }
  },
  mounted() {
    this.userInfo()
  },
  methods: {
    userInfo(){
      if ($nuxt.$store.state.hasLogin) {
        this.user = $nuxt.store.get(process.env.CACHE_PR + "UserInfo");
      }
    },
    userMenu(){
      this.userActive = true
    },
    userMenuOut(){
      this.userActive = false
    },
    logout(){
      this.$store.commit('logout')
      this.$router.push('/')
      this.user = null
    },
  }
}
