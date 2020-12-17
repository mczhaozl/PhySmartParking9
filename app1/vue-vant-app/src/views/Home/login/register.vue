<template>
  <div >      
    <div class="registerContainer"  v-bind:style="{width:'auto',height: mapHeight ,background:background}">
      <div class="registerWarp">
        <van-cell-group class="am-padding-top">
          <van-field v-model="ruserData.username" label="账号昵称" placeholder="请输入账号昵称" />
          <van-field v-model="ruserData.phone" label="手机号码" placeholder="手机号码"  />
          
          <van-field
            v-model="ruserData.password"
            label="密码"
            :type="showPassword ? 'text' : 'password'"
            :right-icon="showPassword ? 'closed-eye' : 'eye-o'"
            placeholder="请输入密码"
            @click-right-icon="passWordType"
          />
          <van-field
            v-model="ruserData.passwordAgain"
            label="确认密码"
            :type="showPassword ? 'text' : 'password'"
            :right-icon="showPassword ? 'closed-eye' : 'eye-o'"
            placeholder="请确认密码"
            @click-right-icon="passWordType"
          />
          
        </van-cell-group>

        <div class="am-margin-top-xl">
          <van-button type="primary" color="linear-gradient(to right, #ff6034, #ee0a24)" round block @click="addUser">注册</van-button>
        </div>
        <div class="forgetPassword am-padding-vertical-sm">
           <sapn  @click="backLogin">返回登录</sapn>
           
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
import { Toast } from 'vant';
import Vue from 'vue';
Vue.use(Toast);

export default {
  name: "register",
  data() {
    return {
      showPassword: false,
      ruserData: {
        username: "",
        password: "",
        phone:"",
        passwordAgain:""
      }
    };

    


  },
  components: {
      Router
    // pageHeader: () => import("@/components/pageHeader/index")
  },
  computed: {

  },
  methods: {
    
     checkPhone (rule, value, callback) {
         if (!(/^1[34578]\d{9}$/.test(value))) {
           callback(new Error('请输入正确的手机号码!'));
         }
     
    }, 

    passWordType() {
      this.showPassword = !this.showPassword;
    },
    // 提交之前表单验证
    validate() {
      if (this.ruserData.username == "") {
        this.$toast("请输入账号");
        return false;
      }else if(!(/^1[34578]\d{9}$/.test(this.ruserData.phone))){
        this.$toast("请输入正确的手机号码");
        return false;
      } else if (this.ruserData.password == "") {
        this.$toast("请输入密码");
        return false;
      }else if (this.ruserData.password != this.ruserData.passwordAgain) {
        this.$toast("两次输入的密码不一致");
        return false;
      }
      return true;
    },
    
    backLogin(){
        this.$router.push("/login");
    },

    addUser() {
        
      let vm = this;
      if (!vm.validate("login")) {
        return false;
      }
      
        postAction(window._CONFIG['domianURL'] + '/sys/user/add', this.ruserData
        ).then(data => {
            // 加载状态结束
        
            if(data.code === 200){
                Toast.success('添加成功');
                this.$router.push("/login");
            }else{
                this.$toast("后台服务器错误，注册失败");
            }
                        
                        
        })

    },


  },
  created() {},
  mounted() {}
};
</script>
<style lang="scss" scoped>

.registerContainer {
    width: 100%;
    // height: 100vh !important;
    top: 0 !important;
    background-image: url("../../../assets/images/background.png");
    background-repeat: no-repeat;
    // background-size: cover;
    background-size:100% ;
  @include container();
  .registerWarp {
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
