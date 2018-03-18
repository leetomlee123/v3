package com.lx.dao;

import com.core.dao.BaseMapper;
import com.lx.entity.User;


import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> checkUserByUserName(String userName);
}
