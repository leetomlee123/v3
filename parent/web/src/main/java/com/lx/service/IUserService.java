package com.lx.service;


import com.core.service.BaseService;
import com.lx.entity.User;

import java.util.List;

public interface IUserService extends BaseService<User> {


    List<User> checkUserByUserName(String userName);
}
