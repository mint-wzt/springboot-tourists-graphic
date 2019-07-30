package com.wzt.uml.config;

import com.wzt.uml.model.Permission;
import com.wzt.uml.model.User;
import com.wzt.uml.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 权限验证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User userWeb = (User) principalCollection.getPrimaryPrincipal();
        String username = userWeb.getUsername();
        User user = userService.getUserByName(username);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> perStringCollection = new HashSet<>();
            rolesCollection.add(user.getRole());
            System.out.println("user.getRole():"+user.getRole());
            List<Permission> permissionList = userService.getPerByID(user.getUser_id());
            for (Permission permission : permissionList) {
                perStringCollection.add(permission.getPermission());
            }
            info.addStringPermissions(perStringCollection);
            info.addRoles(rolesCollection);
            return info;
        } else {
            return null;
        }
    }

    /**
     * 身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (!user.getPassword().equals(String.valueOf(password))){
            throw new IncorrectCredentialsException();
        }
        SecurityUtils.getSubject().getSession().setAttribute("username",username);

        //更新登录时间
        userService.updateTime(user.getUser_id(),new Date());
        String realName = getName();
        ByteSource credentialSalt = ByteSource.Util.bytes(user.getUsername());
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialSalt, realName);
        return authenticationInfo;
    }
}
