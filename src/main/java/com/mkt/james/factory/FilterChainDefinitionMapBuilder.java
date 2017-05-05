package com.mkt.james.factory;

import java.util.LinkedHashMap;

/**
 * Created by jamesche on 2017/5/1.
 * 角色权限工厂Bean。
 */
public class FilterChainDefinitionMapBuilder {
    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/login.jsp ", "anon");
        map.put("/shiro/login", "anon");
        map.put("/admin.jsp", "roles[admin]");
        map.put("/user.jsp", "roles[user]");
        map.put("/shiro/logout", "logout");
        map.put("/**", "authc");
        return map;
    }


}
