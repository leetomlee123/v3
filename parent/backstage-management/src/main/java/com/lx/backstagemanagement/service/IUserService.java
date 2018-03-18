package com.lx.backstagemanagement.service;


import com.lx.backstagemanagement.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {


    User getUserById(String id);

    User save(User user);

    User getName(String name);

    List<User> login(User user);

    Page<User> users(int limit, int offset);

    void update(User user);


    void deleteUser(String uid);
}
