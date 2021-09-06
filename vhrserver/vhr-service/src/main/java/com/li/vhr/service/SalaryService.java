package com.li.vhr.service;

import com.li.vhr.mapper.SalaryMapper;
import com.li.vhr.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalaryService {

    @Autowired
    SalaryMapper salaryMapper;

    public List<Salary> getAllSalaries() {
        return salaryMapper.getAllSalaries();
    }

    public Integer deleteByPrimaryId(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public Integer addData(Salary salary) {
        //没想到
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    public Integer updateTable(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}
