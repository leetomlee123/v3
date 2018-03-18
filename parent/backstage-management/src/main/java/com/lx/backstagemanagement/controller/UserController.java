package com.lx.backstagemanagement.controller;

import com.lx.backstagemanagement.entity.User;
import com.lx.backstagemanagement.service.IUserService;
import com.lx.backstagemanagement.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IUserService userServiceImpl;

    @RequestMapping(value = "/{limit}")
    public String getUsers(@PathVariable("limit") Integer limit, Model model) {
        if (StringUtils.isEmpty(limit)) {
            limit = 0;
        }
        Page<User> users = userServiceImpl.users(limit, 1);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
        model.addAttribute("pageBean", users);
        return "user/list";
    }

    @RequestMapping(value = "/edit/{uid}/{num}")
    public String editUser(@PathVariable("uid") String uid, Model model,@PathVariable("num") String num) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        User userById = userServiceImpl.getUserById(uid);
        //users.getContent().stream().filter(entry -> "1".equals(entry.getUser_category())).collect(Collectors.toList());
        model.addAttribute("model", userById);
        model.addAttribute("num",num);
        return "user/edit";
    }

    @RequestMapping(value = "/delete/{uid}")
    public String deleteUser(@PathVariable("uid")String uid) {

     userServiceImpl.deleteUser(uid);

        return "redirect:/users/0";
    }
    @RequestMapping(value = "/update")
    public String updateUser(User user,String num){
        if(user.getPassword().length()<32){
            user.setPassword(MD5Utils.md5(user.getPassword()));
        }
        userServiceImpl.update(user);
        return "redirect:/users/"+num;
    }
}
