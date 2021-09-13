package com.li.vhr.model;

import java.util.List;


//把分页查询的结果封装成一个模块
public class RespPageBean {

    //一共查了多少条记录
    private Long total;

    //前端要展示的数据
    private List<?> data;

    //分页经常需要用到，如果List集合里面放Employee就写死了，因此里面最好写?，方便后面复用
    //private List<Employee> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
