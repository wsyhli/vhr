import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import {initMenu} from "./utils/menus";

import {postRequest} from "./utils/api";
import {postKeyValueRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import 'font-awesome/css/font-awesome.min.css'

//制作插件
Vue.prototype.postRequest=postRequest;
Vue.prototype.postKeyValueRequest=postKeyValueRequest;
Vue.prototype.putRequest=putRequest;
Vue.prototype.deleteRequest=deleteRequest;
Vue.prototype.getRequest=getRequest;



Vue.config.productionTip = false
Vue.use(ElementUI);

//前置导航守卫
router.beforeEach((to,from,next)=>{
          if(to.path=='/'){
            next();
          }else{
            initMenu(router,store);
            next();
          }
})



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
