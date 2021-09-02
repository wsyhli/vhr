package com.li.vhr.utils;


import com.li.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

//通用工具类
public class HrUtils {
    public static Hr getCurrentHr(){

        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }

}
