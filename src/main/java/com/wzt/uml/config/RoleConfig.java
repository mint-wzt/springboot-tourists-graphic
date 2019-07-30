package com.wzt.uml.config;

import com.wzt.uml.model.Permission;
import com.wzt.uml.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    @Bean
    public User getUser(){
        return new User();
    }

    @Bean
    public Permission getPermission(){
        return new Permission();
    }
}
