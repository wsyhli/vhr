package com.li.vhr.mapper;

import com.li.vhr.model.Position;
import com.li.vhr.model.RespBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPositions();

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);

}