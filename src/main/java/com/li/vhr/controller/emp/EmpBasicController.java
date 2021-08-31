package com.li.vhr.controller.emp;

import com.li.vhr.model.RespPageBean;
import com.li.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    //第一页查询10个数据
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue =
            "10") Integer size){
        return employeeService.getEmployeeByPage(page,size);

    }




}
