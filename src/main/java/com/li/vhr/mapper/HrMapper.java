package com.li.vhr.mapper;

import com.li.vhr.model.Hr;
import com.li.vhr.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//定义crud等一些操作数据库的方法
public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);

}