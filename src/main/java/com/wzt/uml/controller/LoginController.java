package com.wzt.uml.controller;

import com.wzt.uml.config.RoleConfig;
import com.wzt.uml.config.ShiroConfig;
import com.wzt.uml.model.Permission;
import com.wzt.uml.model.User;
import com.wzt.uml.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.management.Util;
import sun.management.counter.Units;

import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private RoleConfig roleConfig;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Object login(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            e.printStackTrace();
            token.clear();
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            token.clear();
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(User user,String checkPW) {
        String username = user.getUsername().trim();
        String password = user.getPassword().trim();
        String checkPw = checkPW.trim();
        System.out.println(user+":"+checkPW);
        if (username.isEmpty()|| password.isEmpty() ||checkPw.isEmpty()){
            //注册信息有误

        }else {
            if (!password.equals(checkPw)){
                //密码与确认密码不正确


            }else {
                String role = user.getRole();
                System.out.println("选择身份：" + role);
                switch (role) {
                    case "普通用户":
                        user.setRole("normal");
                        break;
                    case "高级用户":
                        user.setRole("advanced");
                        break;
                    case "普通管理员":
                        user.setRole("admin");
                        break;
                    default:
                        user.setRole("root");
                }
                userService.register(user); //添加到user表

                User u = userService.getUserByName(username);
                Permission permission = roleConfig.getPermission();
                permission.setUser_id(u.getUser_id());
                permission.setPermission("write");
                userService.addPermission(permission);

            }

        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
