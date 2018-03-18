package com.lx.backstagemanagement.service;


import com.lx.backstagemanagement.entity.Product;
import org.springframework.data.domain.Page;

public interface IProductService {


    Page<Product> products(Integer limit, int i);

    Product getProductById(String pid);

    void update(Product product);


    void delete(String uid);

    void save(Product product);
}
