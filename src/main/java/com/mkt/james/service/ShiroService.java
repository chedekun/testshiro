package com.mkt.james.service;

import org.activiti.engine.impl.interceptor.SessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * Created by jamesche on 2017/5/1.
 * 当使用@transaction注解修饰服务时，不能把shiro注解放在服务上，而是放在Handler上。
 *
 */

public class ShiroService {
    @RequiresRoles({"admin"})
    public void testMethod(){
        System.out.println(new StringBuilder().append("Annotation service called.").append(new Date()).toString());
        //尝试使用Shiro的Session管理功能获取Handler上设置的Session属性；
        Session session = SecurityUtils.getSubject().getSession();
        Object val = session.getAttribute("key");
        System.out.println("Service 里的：" + val);
    }
}
