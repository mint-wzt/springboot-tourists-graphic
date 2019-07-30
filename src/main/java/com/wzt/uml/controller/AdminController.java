package com.wzt.uml.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wzt.uml.model.User;
import com.wzt.uml.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    @RequiresRoles(value = {"admin","root"},logical = Logical.OR)
    public Object getUsersInfo(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users",users);
        return "users-info";
    }
}
