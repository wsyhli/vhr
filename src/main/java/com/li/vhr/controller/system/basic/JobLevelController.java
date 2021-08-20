package com.li.vhr.controller.system.basic;

import com.li.vhr.model.JobLevel;
import com.li.vhr.model.RespBean;
import com.li.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;

    //查询接口
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    //添加接口
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.addJobLevel(jobLevel)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    //更新接口
    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobLevelById(jobLevel)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    //删除接口
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id){
        if(jobLevelService.deleteJobLevelById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    //批量删除接口
    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids){
        if(jobLevelService.deleteJobLevelsByIds(ids)==ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

}















