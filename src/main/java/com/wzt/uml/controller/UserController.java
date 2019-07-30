package com.wzt.uml.controller;

import com.wzt.uml.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


    @RequestMapping("/profile")
    @RequiresPermissions("write")
    public String getProfile(Model model){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        model.addAttribute("username",user.getUsername());
        model.addAttribute("password",user.getPassword());
        model.addAttribute("role",user.getRole());
        model.addAttribute("email",user.getEmail());
        return "profile";
    }
}
