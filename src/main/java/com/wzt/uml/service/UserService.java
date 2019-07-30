package com.wzt.uml.service;

import com.wzt.uml.dao.PermissionMapper;
import com.wzt.uml.dao.UserMapper;
import com.wzt.uml.model.Permission;
import com.wzt.uml.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    public int register(User user){
        return userMapper.insert(user);
    }

    /**
     * 权限
     * @return
     */
    public List<Permission> getPermissions(){
        return permissionMapper.getPermission();
    }

    /**
     * 查询登录用户的所有权限
     * @param user_id
     * @return
     */
    public List<Permission> getPerByID(int user_id){
        return permissionMapper.selectByID(user_id);
    }

    /**
     * 添加用户权限
     * @param permission
     * @return
     */
    public int addPermission(Permission permission){
        return permissionMapper.insert(permission);
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    /**
     * 管理员获取所有的用户信息
     * @return
     */
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    /**
     * 更新用户登录时间
     * @param user_id
     * @param date
     * @return
     */
    public int updateTime(int user_id,Date date){
       return userMapper.updateTime(user_id,date);
    }
}
