package com.li.vhr.service;

import com.li.vhr.mapper.MenuMapper;
import com.li.vhr.mapper.MenuRoleMapper;
import com.li.vhr.model.Hr;
import com.li.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }


    //@Cacheable：缓存注解，这个注解可以不用每次都去查询数据库
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }


    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        return result==mids.length;
    }
}











