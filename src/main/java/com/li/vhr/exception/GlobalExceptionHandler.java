package com.li.vhr.exception;

import com.li.vhr.model.RespBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

//职位管理中删除职位的异常处理
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        if(e instanceof MySQLIntegrityConstraintViolationException){
            return RespBean.error("数据库有关联数据,操作失败");
        }
        return RespBean.error("数据库异常,操作失败");
    }


}
