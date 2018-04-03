package com.lx.backstagemanagement.controller.app;

import com.lx.backstagemanagement.entity.User;
import com.lx.backstagemanagement.service.impl.UserServiceImpl;
import com.lx.backstagemanagement.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/login")
    public Object login(User user) {
        user.setPassword(MD5Utils.md5(user.getPassword()));
        List<User> users = userServiceImpl.login(user);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
