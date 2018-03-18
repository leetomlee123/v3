package com.lx.backstagemanagement.dao;

import com.lx.backstagemanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query(value = "select * from product where pid=:pid", nativeQuery = true)
    Product getProductById(@Param("pid") String pid);

    @Modifying
    @Transactional
    @Query(value = "update product set pflag=1 where pid=:uid", nativeQuery = true)
    void deleteProduct(@Param("uid") String uid);
}
