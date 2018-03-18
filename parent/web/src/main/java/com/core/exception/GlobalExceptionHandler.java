package com.core.exception;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    private final static String EXPTION_MSG_KEY = "message";
//
//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody
//    public void handleBizExp(HttpServletRequest request, Exception ex) {
//        LOG.info("Business exception handler  " + ex.getMessage());
//        request.getSession(true).setAttribute(EXPTION_MSG_KEY, ex.getMessage());
//    }
//
//    @ExceptionHandler(SQLException.class)
//    public ModelAndView handSql(Exception ex) {
//        LOG.info("SQL Exception " + ex.getMessage());
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("message", ex.getMessage());
//        mv.setViewName("exception/500");
//        return mv;
//    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //handler就是处理器适配器要执行的Handler对象(只有method)
        //解析出异常类型。

        //如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示。
        BusinessException customException = null;
        if (ex instanceof BusinessException) {
            customException = (BusinessException) ex;

        } else {
            //如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）。
            customException = new BusinessException("未知错误");
        }

        //错误信息
        String message = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();

        //将错误信息传到页面
        modelAndView.addObject("message", message);
        modelAndView.addObject("title", 500);
        //指向到错误界面
        modelAndView.setViewName("exception/404");
        System.out.println(modelAndView.getViewName());
        return modelAndView;
    }

}