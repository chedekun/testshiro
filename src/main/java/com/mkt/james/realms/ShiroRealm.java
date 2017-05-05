package com.mkt.james.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by jamesche on 2017/4/26.
 * 如果单纯的认证，直接继承 AuthenticationRealm 就可以了。
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //System.out.println("doGetAuthenticationInfo" + token);

       // 1. 把AuthenticationToken 转换为 userNamepasswordTocken

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 2. 从token中获取Username
        String username = upToken.getUsername();
        // 3. 调用数据库方法，获取username 对应的记录
        System.out.println("从数据库中获取的username： " + username + " 所对应的信息");
        // 4. 若用户不存在，跑一场
        if ("unknown".equals(username)) {
            throw new UnknownAccountException(username + " 用户不存在！");
        }
        // 5. 若用户被锁定，抛异常
        if ("monster".equals(username)) {
            throw new LockedAccountException(username + " 用户被锁定！");
        }
        // 6.根据用户的情况，构建AuthenticationInfo 对象并返回。 一下信息从数据库获取。
        Object principle = username ;
        Object credentials = "123456";
        String realmName = getName(); //父类的方法。
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principle,credentials,realmName);
        return info;





    }

    /**
     * 授权
     * 20170430 James
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       System.out.println("Authorizing....");
      // 1. 从PrincipalCollection 中获取登录用户的信息
       Object principal = principals.getPrimaryPrincipal();
      //2 .利用登录的用户信息获取当前用户角色或权限（可能需要查询数据库）
       Set<String> roles = new HashSet<>();
       roles.add("user");
       if ("admin".equals(principal)) {
           roles.add("admin");
       }
       //3. 创建simpleAuthorizationInfo, 并添加roles属性。
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        // 4. 返回
        return info;
    }
}
