package com.li.vhr.service;

import com.li.vhr.mapper.EmployeeMapper;
import com.li.vhr.model.Employee;
import com.li.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size) {
        if(page!=null&&size!=null){
            page=(page-1)*size;
        }
        List<Employee> data =employeeMapper.getEmployeeByPage(page,size);
        Long total = employeeMapper.getTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;


    }
}
