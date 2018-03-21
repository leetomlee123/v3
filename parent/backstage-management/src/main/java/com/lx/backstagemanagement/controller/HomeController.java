package com.lx.backstagemanagement.controller;

import com.alibaba.fastjson.JSON;
import com.lx.backstagemanagement.RabbitMqProduct.SimpleProduct;
import com.lx.backstagemanagement.constant.SysKeyWord;
import com.lx.backstagemanagement.entity.User;
import com.lx.backstagemanagement.service.impl.UserServiceImpl;
import com.lx.backstagemanagement.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(SysKeyWord.getUserName());
        return "index";
    }

    @RequestMapping(value = "/user/login")
    public String login(User user, HttpServletResponse response, HttpServletRequest request) {
        user.setPassword(MD5Utils.md5(user.getPassword()));
        List<User> users = userServiceImpl.login(user);
        HttpSession session = request.getSession(false);
        if (users.size() >= 1) {
            session.setAttribute(SysKeyWord.getUserName(), users.get(0));

            // model.addAttribute("USER_NAME", users.get(0));
            return "redirect:/index";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/top")
    public String top() {
        return "top";
    }

    @RequestMapping(value = "/left")
    public String left() {
        return "left";
    }

    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/bottom")
    public String bottom() {
        return "bottom";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "home";
    }
}
