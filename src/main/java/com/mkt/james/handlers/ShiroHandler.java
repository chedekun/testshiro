package com.mkt.james.handlers;

import com.mkt.james.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by jamesche on 2017/4/27.
 */
@Controller

public class ShiroHandler {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroHandler.class);
    @Autowired


    private ShiroService shiroService;

    @RequestMapping("/shiro/testShiroService")
    public String testShiroService(HttpSession httpSession){
        httpSession.setAttribute("key","value:123456");
        shiroService.testMethod();
        return "redirect:/list.jsp";
    }

    @RequestMapping("/shiro/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();


        // let's login the current user so we can check against roles and permissions:
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            }

//            catch (UnknownAccountException uae) {
//                log.info("There is no user with username of " + token.getPrincipal());
//            } catch (IncorrectCredentialsException ice) {
//                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
//            } catch (LockedAccountException lae) {
//                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
//                        "Please contact your administrator to unlock it.");
//            }
//            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("Login fail , do you know?" + ae.getMessage());
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");


        return "redirect:/list.jsp";
    }
}
