package com.lx.service;


import com.core.service.BaseServiceImpl;
import com.lx.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserService extends BaseServiceImpl<User> implements IUserService {


    public List<User> checkUserByUserName(String userName) {
        return super.userMapper.checkUserByUserName(userName);
    }
}
