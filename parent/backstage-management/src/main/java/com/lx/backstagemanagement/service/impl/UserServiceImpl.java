package com.lx.backstagemanagement.service.impl;

import com.lx.backstagemanagement.annotation.RedisCache;
import com.lx.backstagemanagement.dao.UserDao;
import com.lx.backstagemanagement.entity.User;
import com.lx.backstagemanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements IUserService {
    @Autowired
    @Qualifier(value = "userDao")
    private UserDao userDao;

    @Override


    public User getUserById(String id) {
        return userDao.getUserByUid(id);
    }


    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getName(String name) {
        return userDao.getName(name);
    }

    @Override
    public List<User> login(User user) {
        return userDao.login(user.getUsername(), user.getPassword());
    }

    @Override
    public Page<User> users(int limit, int offset) {
        Pageable pageable = new PageRequest(limit, offset);
        Specification<User> spec = new Specification<User>() {
            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> user_category = root.get("user_category");
                javax.persistence.criteria.Predicate name = criteriaBuilder.equal(user_category, "2");
                return name;
            }        //查询条件构造
        };
        return userDao.findAll(spec, pageable);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public void update(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String uid) {
        User user = new User();
        user.setUid(uid);
        userDao.delete(user);
    }
}
