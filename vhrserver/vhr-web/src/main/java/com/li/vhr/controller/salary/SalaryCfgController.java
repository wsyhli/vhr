package com.li.vhr.controller.salary;

import com.li.vhr.model.Employee;
import com.li.vhr.model.RespPageBean;
import com.li.vhr.model.Salary;
import com.li.vhr.service.EmployeeService;
import com.li.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalaryCfgController {

    @Autowired
    SalaryService salaryService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1")Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeByPageWithSalary(page,size);
    }










}
