package com.li.vhr.controller.salary;

import com.li.vhr.model.RespBean;
import com.li.vhr.model.Salary;
import com.li.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    //删除接口
    @DeleteMapping("/{id}")
    public RespBean deleteByPrimaryId(@PathVariable Integer id){
        if(salaryService.deleteByPrimaryId(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");

    }

    //添加接口(即插入)
    @PostMapping("/")
    public RespBean addData(@RequestBody Salary salary){
        if(salaryService.addData(salary)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    //更新接口
   @PutMapping("/")
    public RespBean updateTable(@RequestBody Salary salary){
        if(salaryService.updateTable(salary)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
   }



}
