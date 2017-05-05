package com.mkt.james.handlers;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jamesche on 2017/5/1.
 * 用于集中处理所有可捕获的异常。可以Handler上的所有里外处理程序放在这里。
 * 此时的类需要使用@ControllerAdvice注解标记。
 */
@ControllerAdvice
public class HandleExceptions {
    /**
     * 当角色权限不足时，显示此错误处理
     */
    @ExceptionHandler({UnauthorizedException.class})
    public ModelAndView handleUnauthorizedException(Exception ex){

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("Exception",ex);
        return mv;
    }
}
