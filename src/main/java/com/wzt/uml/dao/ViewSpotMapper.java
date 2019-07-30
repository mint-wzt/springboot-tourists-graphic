package com.wzt.uml.dao;

import com.wzt.uml.model.ViewSpot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ViewSpotMapper {

    @Select({"select * from viewspot where city=#{city}"})
    List<ViewSpot> getSightByName(String city);

    @Select({"select * from viewspot where id=#{id}"})
    ViewSpot getSightByID(int id);
}
