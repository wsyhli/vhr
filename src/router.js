import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
//引入Home组件
import Home from './views/Home.vue'
import FriendChat from './views/chat/FriendChat.vue'



//官网给的示例
//import Vue from 'vue'
//import VueRouter from 'vue-router'
//Vue.use(VueRouter)


//安装路由功能，松哥说的好像VueRouter和Router作用差不多
Vue.use(Router)


//创建 router 对象，然后传 `routes` 配置
export default new Router({
  routes:[
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden:true
    },{
      path:'/home',
      name:'Home',
      component:Home,
      hidden:true,
      children:[
        {
          path: '/chat',
          name: '在线聊天',
          component: FriendChat,
          hidden:true
        }
      ]
    },

  ]
})
