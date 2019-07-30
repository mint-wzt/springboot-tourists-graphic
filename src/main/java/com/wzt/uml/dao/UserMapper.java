package com.wzt.uml.dao;

import com.wzt.uml.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface UserMapper {
    @Insert({"insert into user(username,password,role,email) values(#{username},#{password},#{role},#{email})"})
    int insert(User user);

    @Select({"select * from user where user_id=#{user_id}"})
    User selectByID(int user_id);

    @Select({"select * from user where username=#{username}"})
    User getUserByName(String username);

    @Select({"select * from user"})
    List<User> getAllUser();

    @Update({"update user set login_time=#{date} where user_id=#{user_id}"})
    int updateTime(int user_id,Date date);


}
