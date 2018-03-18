package com.lx.controller;


import com.core.constant.SysKeyWord;
import com.lx.entity.User;
import com.lx.service.IUserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.MD5Utils;
import utils.MailUtils;
import utils.UUIDUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private IUserService userService;

//    @Resource
//    private HttpServletRequest request;
//    private String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

    @RequestMapping("/login")
    public String login(User user, String validateCode, HttpServletRequest request, Model model) {
        if (!StringUtils.isNotBlank(validateCode) || user == null) {
            model.addAttribute("cause", "验证码或数据有错");
            return "common/login";
        }
        HttpSession session = request.getSession(false);
        String validateCodeSession = (String) session.getAttribute("validateCode");
        if (validateCodeSession == null) {
            model.addAttribute("cause", "验证码已过期");
            model.addAttribute("username", user.getName());
            return "common/login";
        }
        if (!validateCode.equalsIgnoreCase(validateCodeSession)) {
            model.addAttribute("cause", "验证码有误");
            model.addAttribute("user", user);
            return "common/login";
        }
        user.setPassword(MD5Utils.md5(user.getPassword()));
        List<User> users = userService.select(user);
        if (users.size() < 1) {
            model.addAttribute("cause", "账户名或密码有错");
            return "common/login";
        }
        if (users.get(0).getState() == 0) {
            model.addAttribute("cause", "账户尚未激活");
            return "common/login";
        }
        session.setAttribute(SysKeyWord.getUserName(), users.get(0));
        System.out.println("success");
        Object backUrl = session.getAttribute("backUrl");
        if (backUrl == null) {
            backUrl = "redirect:/";
        } else {
            backUrl = "redirect:" + backUrl.toString();
        }
        return backUrl.toString();
    }

    @RequestMapping(value = "/loginUI")
    public String loginUI(HttpServletRequest request) {
        String backUrl = request.getParameter("backUrl");
        request.getSession().setAttribute("backUrl", backUrl);
        return "common/login";
    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(SysKeyWord.getUserName());
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return "redirect:" + basePath;
    }

    @RequestMapping(value = "/checkUserName")
    public @ResponseBody
    Map<String, String> checkUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        List<User> users = userService.select(user);
        Map<String, String> resultMap = new HashMap<String, String>();
        if (users.size() > 0) {
            resultMap.put("code", "true");
        }
        return resultMap;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, String checkCode, Model model, HttpServletRequest request) {
        if (!StringUtils.isNotBlank(checkCode) || user == null) {
            model.addAttribute("cause", "验证码或数据有错");
            return "register/register";
        }
        HttpSession session = request.getSession(false);
        String validateCodeSession = (String) session.getAttribute("validateCode");
        if (validateCodeSession == null) {
            model.addAttribute("cause", "验证码已过期");
            model.addAttribute("username", user.getName());
            return "register/register";
        }
        if (!checkCode.equalsIgnoreCase(validateCodeSession)) {
            model.addAttribute("cause", "验证码有误");
            return "register/register";
        }
        user.setUid(UUIDUtils.getUUID());
        user.setPassword(MD5Utils.md5(user.getPassword()));
        user.setUser_category(1);
        try {
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            int insert = userService.insert(user);
            MailUtils.sendEmail(user.getEmail(), "账号激活", basePath + "/user/accountActive?code=" + user.getUid());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "register/registerSuccess";
    }

    @RequestMapping(value = "/accountActive")
    public @ResponseBody
    String accountActive(String code) throws Exception {
        User user = new User();
        user.setUid(code);
        List<User> users = userService.select(user);
        user = users.get(0);
        user.setCode("1");
        userService.update(user);
        return "激活成功";
    }

    @RequestMapping(value = "/to_login")
    public String login(String code) throws Exception {

        return "common/login";
    }


}
