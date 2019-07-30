package com.wzt.uml.dao;

import com.wzt.uml.model.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PermissionMapper {

    @Select({"select * from permission where user_id=#{user_id}"})
    List<Permission> selectByID(int user_id);

    @Insert({"insert into permission(user_id,permission) values(#{user_id},#{permission})"})
    int insert(Permission permission);

    @Select({"select * from permission"})
    List<Permission> getPermission();

}
