<template>
  <div>
    <el-container>
      <el-header class="homeHeader">
        <div class="title">微人事</div>
        <div>
          <el-button icon="el-icon-bell" type="text" style="margin-right: 8px;color: #000000;" size="normal" @click="goChat"></el-button>
          <el-dropdown class="userInfo" @command="commandHandler">
  <span class="el-dropdown-link">
    {{user.name}} <el-image :src="user.userface"></el-image>
  </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
              <el-dropdown-item command="settings">设置</el-dropdown-item>
              <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu router>
<!--            v-for="(item,index) in this...中的in和左边有空格-->
            <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
              <template slot="title">
                <i style="color: #1573d2;margin-right: 5px":class="item.iconCls"></i>
                <span>{{item.name}}</span>
              </template>
                <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">{{child.name}}</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <!--Breadcrumb 面包屑制作导航栏-->
          <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!='/home'">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <!--v-if="this.$router.currentRoute.path='/home'"使得只在首页显示欢迎来到微人事!-->
          <div class="homeWelcome" v-if="this.$router.currentRoute.path=='/home'">
            欢迎来到微人事!
          </div>
          <router-view class="homeRouterView"/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      // 从 sessionStorage 获取数据
      // user:JSON.parse(window.sessionStorage.getItem("user")),
      // src: 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg'
    }
  },
  computed:{
    routes(){
      return this.$store.state.routes;
    },
    user(){
      return this.$store.state.currentHr;
    }
  },
  methods:{
    goChat(){
      this.$router.push("/chat");
    },
    commandHandler(cmd){
      if(cmd=='logout'){
        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.getRequest("/logout");
            window.sessionStorage.removeItem("user")
            //为了让注销登陆后，下一个用户的菜单栏不是上一个用户的菜单栏，清除缓存
            this.$store.commit('initRoutes',[]);
            this.$router.replace("/");
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      }
    }
  }
}
</script>

<style>
/*有些样式在Home页里面整体来改,不要在单个组件上改*/
.homeRouterView{
  margin-top: 10px;
}
.homeWelcome{
  text-align: center;
  font-size: 30px;
  font-family: 华文行楷;
  color:#1573d2;
  padding-top:50px;
}
.homeHeader{
    background:#1573d2;
    display:flex;
    align-items:center;
    justify-content: space-between;
    padding:0px 15px;
    box-sizing: border-box;
}
.title{
  font-size:30px;
  font-family: 华文行楷;
  color:#ffffff;
}
.userInfo{
  /*鼠标会变成手的样子*/
  cursor: pointer;
}
.el-dropdown-link img{
  width:48px;
  height:48px;
  border-radius: 24px;
  margin-left: 8px;
}
.el-dropdown-link{
  font-size: 20px;
  color:#ffffff;
  display: flex;
  align-items: center;
}


</style>