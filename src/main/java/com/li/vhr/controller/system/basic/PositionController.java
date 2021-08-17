package com.li.vhr.controller.system.basic;

import com.li.vhr.model.Position;
import com.li.vhr.model.RespBean;
import com.li.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    //查询接口
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    //添加角色接口
    @PostMapping("/")
    //@RequestBody会把前端传过来的数据反序列化为一个对象(来自官方文档)
    //通过position对象接收前端传过来的json数据
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");

    }

    //更新接口
    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position){
        if (positionService.updatePositions(position)==1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    //删除接口
    //请求地址中的参数会被传递到id这个变量上去
    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id){
        if(positionService.deletePositionById(id)==1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    //批量删除
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids){
        if(positionService.deletePositionsByIds(ids)==ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }



}
