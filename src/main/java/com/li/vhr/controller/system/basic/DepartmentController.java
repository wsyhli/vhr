package com.li.vhr.controller.system.basic;

import com.li.vhr.model.Department;
import com.li.vhr.model.RespBean;
import com.li.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        departmentService.addDep(dep);
        if(dep.getResult()==1){
            return RespBean.ok("添加成功",dep);
        }
        return RespBean.error("添加失败");
    }



}
