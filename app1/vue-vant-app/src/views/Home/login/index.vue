<template>
  <div >
    <!-- <pageHeader title="南昌高新停车" :leftIcon="false" :rightIcon="false"></pageHeader> -->

      
    <div class="container"  v-bind:style="{width:'auto',height: mapHeight ,background:background}">
      <div class="loginWarp">
        <van-cell-group class="am-padding-top">
          <van-field v-model="userData.username" label="账号" placeholder="请输入账号" />
          <van-field
            v-model="userData.password"
            label="密码"
            :type="showPassword ? 'text' : 'password'"
            :right-icon="showPassword ? 'closed-eye' : 'eye-o'"
            placeholder="请输入密码"
            @click-right-icon="passWordType"
          />
        </van-cell-group>

        <div class="am-margin-top-xl">
          <van-button type="info" color="linear-gradient(to right, #ff6034, #ee0a24)" round block @click="login">登录</van-button>
        </div>
        <div class="forgetPassword am-padding-vertical-sm">
           <sapn   @click="register">立即注册！</sapn>
           <sapn   @click="forgetPassword">忘记密码？</sapn>
        </div>
       
      </div>
    </div>
  </div>
</template>

<script>
import { captcha } from "@/api/common";
import { checkPhone, checkCode } from "@/utils/validate";
import { mapGetters, mapMutations, mapActions } from "vuex";
import {postAction} from '@/api/manage';
import Router from "vue-router";
import VueRouter from 'vue-router';
import background from '@/assets/images/background.png'

export default {
  name: "Login",
  data() {
    return {
      showPassword: false,
      userData: {
        name: "",
        password: ""
      }
    };
  },
  components: {
      Router
    // pageHeader: () => import("@/components/pageHeader/index")
  },
  computed: {
    ...mapGetters([
      "userInfo" // 用户信息
    ]),
    baseUrl() {
      return process.env.VUE_APP_BASE_URL;
    }
  },
  methods: {
    /**
     * 忘记密码
     */
    forgetPassword() {
      console.log("忘记密码");
    },
    register() {
      this.$router.push("/register");
    },
    /**
     * 查看密码形式转换
     */
    passWordType() {
      this.showPassword = !this.showPassword;
    },
    // 提交之前表单验证
    validate() {
      if (this.userData.username == "") {
        this.$toast("请输入账号");
        return false;
      } else if (this.userData.password == "") {
        this.$toast("请输入密码");
        return false;
      }
      return true;
    },
    // 登录
    async login() {
      let vm = this;
      // 提交验证
      if (!vm.validate("login")) {
        return false;
      }
      // 获取登录接口数据
  
      postAction(window._CONFIG['domianURL'] + '/sys/mLogin', {
                        'username': vm.userData.username,
                        'password': vm.userData.password
                    }
                ).then(data => {
                    // 加载状态结束
                    if(data.code === 200){
                      localStorage.setItem("token",data.result.token);
                      localStorage.setItem("realname",data.result.userInfo.realname);
                      localStorage.setItem("userName",data.result.userInfo.username);
                      localStorage.setItem("phone",data.result.userInfo.phone);
                      localStorage.setItem("isLogin","true");
                      this.$router.push("/me");
                      
                    }
                    
                    
                })
      // const { data } = await login(vm.userData);
      // console.log(data);
    }
  },
  created() {},
  mounted() {}
};
</script>
<style lang="scss" scoped>

.container {
    width: 100%;
    // height: 100vh !important;
    top: 0 !important;
    background-image: url("../../../assets/images/background.png");
    background-repeat: no-repeat;
    // background-size: cover;
    background-size:100% ;
  @include container();
  .loginWarp {
    @include commonWarp();
    margin-top: 7.53333rem;
    background-color:transparent;
    .forgetPassword {
      font-size: $textSm;
      color: $colorMain;
      line-height: 24px;
      text-align: right;
    }
  }
}
</style>
