package com.lx.backstagemanagement.intercept;

import com.lx.backstagemanagement.constant.SysKeyWord;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {


    //拦截钱处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {


        if (request.getRequestURI().contains("/app")||request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/") || request.getRequestURI().equals("/health")) {
            return true;
        }
        Object sessionObj = request.getSession().getAttribute(SysKeyWord.getUserName());
        if (sessionObj != null) {
            return true;
        } else {

            response.sendRedirect("/");
        }
        return false;
    }

    //拦截后处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
    }

    //全部完成后处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
    }
} 