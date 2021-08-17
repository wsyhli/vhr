//导入Vue对象本身
import Vue from 'vue'
import App from './App.vue'
//从router.js中导入，其中.js可以省略
import router from './router'
import store from './store'
//引用elementui中的部分组件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//
import {initMenu} from "./utils/menus";
//引入封装好的方法
import {postRequest} from "./utils/api";
import {postKeyValueRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import 'font-awesome/css/font-awesome.min.css'

//制作插件，将封装好的方法挂载到Vue.prototype上
Vue.prototype.postRequest=postRequest;
Vue.prototype.postKeyValueRequest=postKeyValueRequest;
Vue.prototype.putRequest=putRequest;
Vue.prototype.deleteRequest=deleteRequest;
Vue.prototype.getRequest=getRequest;


Vue.config.productionTip = false

//通过全局方法 Vue.use() 使用插件
Vue.use(ElementUI);

//前置导航守卫
router.beforeEach((to,from,next)=>{
          if(to.path=='/'){
            next();
          }else{
              if(window.sessionStorage.getItem("user")){
                  initMenu(router,store);
                  next();
              }else{
                  //访问http://localhost:8080/sys/basic失败,就去登录页
                  // next('/');
                  //在登录页面访问http://localhost:8080/sys/basic,再点击登录按钮,会直接跳转到"基础信息设置"这一栏
                  next('/?redirect='+to.path);
              }
          }
})

//创建一个Vue对象
new Vue({
  //将router注入进来，让本对象具有路由功能
  router,
  store,
    // 将App(APP.vue)渲染后挂载到id属性为app的元素上，即index.html中的<div id="app"></div>上
  render: h => h(App)
    //将vue的实例挂载到id属性为app的元素上，即index.html中的<div id="app"></div>上
    // 这是vue3推荐的挂载方式，vue2中的挂载方式用的是el：‘#app’
}).$mount('#app')
