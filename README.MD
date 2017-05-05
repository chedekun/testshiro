1. 配置 web.xml
- 引入Spring
    - contextParam
    - listener         
- 引入Shiro
    - org.springframework.web.filter.DelegatingFilterProxy    
- 引入SpringMVC
    - org.springframework.web.servlet.DispatcherServlet 
2. 配置 applicationcontext.xml
    - shiro
        - SecurityManager 
        - CacheManager
        - Realm
        - bean to implement of Realm .
        - lifecycleBeanPostProcessor
        - DefaultAdvisorAutoProxyCreator
        - shiroFilter (*)
    - others

3. 配置 springmvc-servlet.xml
    - <context:component-scan>
    - org.springframework.web.servlet.view.InternalResourceViewResolver
    - <mvc:annotation-driven>
    - <mvc:default-servlet-handler>
    
4. 认证过程
    - 获取当前的Subject. 调用SecurityUtils.getSubject();
    - 测试当前用户是否被认证。即是否已经登录。 调用Subject的isAuthenticated().
    - 若没有被认证，则把用户名和密码封装为UsernamePasswordToken对象。
        - 创建一个表单页面
        - 把请求提及告给SpringMVC的handler
        - 获取用户名和密码
    - 执行登录
        - 调用subject 的login（AuthenticationToken）方法
    - 自定义RealM的方法，从数据库中获取相应的记录，返回给realM.
        - 需要继承org.apache.shiro.realm.AuthenticationRealM类。
        - 实现 doGetAuthenticationInfo(AuthenticationToken)方法。
    - 由Shiro 完成对密码的比对。
        - 密码的比对是通过AuthenticationRealm的credentialsMatcher属性来进行的。
        - 使用HashedCrendentialsMatcher对象，并设置加密算法即可。（如何为字符串设置MD5加密）
        - 使用盐值加密的方法：
            - 在doGetAuthenticationInfo方法返回值中创建SimpleAuthenticationInfo 对象的时候，需要使用SimpleAuthenticationInfo(principla,credentianls,credentialsSalt,realMName)构造器
            - 使用ByteSource.Util.bytes()来计算盐值。
            - 使用new simpeHash(hashAlgorithName,credentials,salt,hashIterations); 来计算盐值加密后的密码的值。
            
5. 授权
    - Realm需要继承AuthorizingReaml类，并实现其doGetAuthorizationInfo方法。他可以同时实现认证和授权功能。
    - 从PrincipalCollection 中获取登录用户的信息。
    - 利用登录用户的信息来获取当前用户的角色或权限（可能需要查询数据库）
    - 创建SimpleAuthorizationInfo,并设置Role属性。
    - 返回SimpleAuthorizationInfo 对象。