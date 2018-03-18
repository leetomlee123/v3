package com.lx.backstagemanagement.dao;

import com.lx.backstagemanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryDao extends JpaRepository<Category,Long> {
    @Query(value = "select * from category where cid=:cid",nativeQuery = true)
    Category findByCid(@Param(value = "cid") String cid);
}
