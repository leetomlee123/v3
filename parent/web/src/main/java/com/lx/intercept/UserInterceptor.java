package com.lx.intercept;

import com.core.constant.SysKeyWord;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {


    //拦截钱处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        Object sessionObj = request.getSession().getAttribute(SysKeyWord.getUserName());
        if (sessionObj != null) {
            return true;
        }
        String requestURI = request.getRequestURI();

        request.getSession().setAttribute("backUrl",  requestURI);
        response.sendRedirect("/user/to_login");
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