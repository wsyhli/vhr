package com.li.vhr.service;

import com.li.vhr.mapper.MenuMapper;
import com.li.vhr.model.Hr;
import com.li.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }


    //@Cacheable：缓存注解，这个注解可以不用每次都去查询数据库
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }


}
