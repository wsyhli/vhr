<template>
<div>
  <!--:model等价于v-bind:model-->
  <el-form :rules="rules"
           ref="loginForm"
           v-loading="loading"
           element-loading-text="正在登录...."
           element-loading-spinner="el-icon-loading"
           element-loading-background="rgba(0, 0, 0, 0.8)"
           :model="loginForm"
           class="loginContainer">
    <h3 class="systemLogin">系统登录</h3>
    <el-form-item prop="username">
      <el-input size="normal" type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <!--@keydown.enter.native="submitLogin使得当光标停在密码输入框内的时候,可以按回车进行登录-->
      <el-input size="normal" type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" @keydown.enter.native="submitLogin"></el-input>
    </el-form-item>
    <el-checkbox size="normal" class="loginRemember" v-model="checked"></el-checkbox>
    <el-button size="normal" type="success" style="width:100%" @click="submitLogin">登录</el-button>
  </el-form>
</div>
</template>

<script>

//模块化的思想
export default {
  //name属性便于调试和使用
  //就是当我们使用 keep-alive时可以使用include和exclude指定需要缓存和不需要缓存的组件。指定的依据就是组件的name。
  name: "Login",
  data(){
   return{
     loading:false,
     loginForm:{
       username:'admin',
       password:'123'
     },
     checked:true,
     rules:{
          username:[{required:true,message:'请输入用户名',trigger:'blur'}],
          password:[{required:true,message:'请输入密码',trigger:'blur'}]
     }
   }
  },
  methods:{
    submitLogin(){

      //=>箭头函数(可以看廖雪峰的官网)
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading=true;
          //在main.js中导入制作好的插件后，以后在vue文件中，直接通过this就可以获取到网络请求方法的引用了
          this.postKeyValueRequest('/doLogin',this.loginForm).then(resp=>{
            this.loading=false;
            if(resp){
              this.$store.commit('INIT_CURRENTHR',resp);
              //前端存储数据的地方:sessionStorage,localStorage,vuex
              //保存数据到sessionStorage
              window.sessionStorage.setItem("user",JSON.stringify(resp));
              //通过注入路由器，我们可以在任何组件内通过 this.$router 访问路由器，也可以通过 this.$route 访问当前路由
              //想要导航到不同的 URL，则使用 router.push 方法。这个方法会向 history 栈添加一个新的记录，所以，当用户点击浏览器后退按钮时，则回到之前的 URL
              //router.replace跟 router.push 很像，唯一的不同就是，它不会向 history 添加新记录，而是跟它的方法名一样 —— 替换掉当前的 history 记录。
              let path =this.$route.query.redirect;
              this.$router.replace((path=='/'||path==undefined)?'/home':path);
            }
          })
          // alert('submit!');
        } else {
          this.$message.error('请输入相关信息');
          return false;
        }
      });
    }
  }
}

</script>

<style>
   .loginContainer{
    /*设置圆角*/
    border-radius:15px;
    /*修改背景的区域*/
    background-clip:padding-box;
    margin:250px auto;
    width:300px;
    padding:35px 35px 15px 35px;
    background: #ffffff;
    /*下面是border的简写，solid定义实线边框*/
    border:1px solid #eaeaea;
    box-shadow:0 0 25px #cac6c6;
  }
  .systemLogin{
    margin:10px auto 20px auto;
    text-align:center;
    color: #1573d2;
  }
  .loginRemember{
    text-align:left;
    margin:0px 0px 25px 0px;
  }


</style>