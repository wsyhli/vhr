//导入axios组件
import axios from "axios";
//引入ElementUI中的Message组件来展示错误信息
import { Message } from 'element-ui';
import router from '../router';

//定义一个响应拦截器
//箭头函数中的success表示服务端处理成功的响应
axios.interceptors.response.use(success=>{
    if(success.status&&success.status==200&&success.data.status==500){
        Message.error({message:success.data.msg})
        return;
    }
    //响应成功会弹出提示信息
    if(success.data.msg){
        Message.success({message:success.data.msg})
    }
    //将服务端返回的数据return,这个数据最终会来到请求调用的地方
    return success.data;
    //箭头函数中的error表示服务端处理失败的响应
},error=>{
    if(error.response.status==504||error.response.status==404){
        Message.error({message:'服务器被吃了'})
    }else if(error.response.status==403){
        Message.error({message:'权限不足，请联系管理员'})
    }else if(error.response.status==401){
        Message.error({message:'尚未登录，请登录'})
        router.replace('/');
    }else{
        if(error.response.data.msg){
            Message.error({message:error.response.data.msg})
        }else{
            Message.error({message:'未知错误'})
        }
    }
    return;
})

//定义一个base变量，这是请求的前缀，方便后期维护
let base='';
//export用来封装输出模块
//把一个箭头函数存储在常量postKeyValueRequest中
export const postKeyValueRequest=(url,params)=>{
    return axios({
        //请求方法
        method:'post',
        url:`${base}${url}`,
        data:params,
        transformRequest:[function (data) {
            let ret ='';
            for(let i in data){
                ret=ret+encodeURIComponent(i)+'='+encodeURIComponent(data[i])+'&'
            }
            return ret;
        }],
        headers:{
            'Content-Type':'application/x-www-form-urlencoded'
        }
    });
}

//四种方法封装
export const postRequest=(url,params)=>{
    return axios({
        method:'post',
        url:`${base}${url}`,
        data:params
    })
}
export const putRequest=(url,params)=>{
    return axios({
        method:'put',
        url:`${base}${url}`,
        data:params
    })
}
//函数表达式是一个常量，存储在常量getRequest中，getRequest可以当作一个函数来用
export const getRequest=(url,params)=>{
    return axios({
        method:'get',
        url:`${base}${url}`,
        data:params
    })
}
export const deleteRequest=(url,params)=>{
    return axios({
        method:'delete',
        url:`${base}${url}`,
        data:params
    })
}







