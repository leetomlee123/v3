package com.lx.service;


import com.core.service.BaseService;
import com.lx.entity.Orders;
import com.lx.entity.Product;

import java.util.List;

public interface IProductService extends BaseService<Product> {

    List<Product> getHotProduct();

    List<Product> getNewHotProduct();
}
