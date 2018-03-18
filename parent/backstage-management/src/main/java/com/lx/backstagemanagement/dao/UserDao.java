package com.lx.backstagemanagement.dao;


import com.lx.backstagemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from user where name=:name", nativeQuery = true)
    User getName(@Param("name") String name);


    @Query(value = "select * from user where username=:name and password=:password and user_category=2", nativeQuery = true)
    List<User> login(@Param("name") String name, @Param("password") String password);

    //    @Query(value = "select * from user where user_category=2 and limit ?1,?2", nativeQuery = true)
//    List<Page> queryUsersByUser_category(@Param("limit") int limit, @Param("offset") int offset);
    // @Query(value = "select * from user where uid=?1")
    @Query(value = "select * from user where uid=:uid and user_category=2", nativeQuery = true)
    User getUserByUid(@Param("uid") String uid);


}
