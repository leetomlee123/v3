package com.lx.filter;


import com.core.constant.SysKeyWord;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String a = request.getRequestURI();
        if ((a.contains(".png") || a.contains(".jpg")|| a.contains(".gif"))) {
            //如果发现是css或者js文件，直接放行
                String s = SysKeyWord.getImageAddress() + a;
                response.sendRedirect(s);
        }
        //在else中放对网页过滤的代码
        else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
