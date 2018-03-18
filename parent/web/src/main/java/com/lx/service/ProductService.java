package com.lx.service;


import com.core.service.BaseServiceImpl;
import com.lx.entity.Orders;
import com.lx.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "productService")
public class ProductService extends BaseServiceImpl<Product> implements IProductService {

    @Override
    public List<Product> getHotProduct() {
        return productMapper.getHotProduct();
    }

    @Override
    public List<Product> getNewHotProduct() {
        return productMapper.getNewHotProduct();
    }
}
