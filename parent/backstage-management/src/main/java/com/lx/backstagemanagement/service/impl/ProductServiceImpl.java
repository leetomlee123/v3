package com.lx.backstagemanagement.service.impl;

import com.lx.backstagemanagement.dao.ProductDao;
import com.lx.backstagemanagement.entity.Product;
import com.lx.backstagemanagement.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service(value = "productServiceImpl")
public class ProductServiceImpl implements IProductService {
    @Autowired
    @Qualifier(value = "productDao")
    private ProductDao productDao;

    @Override
    public Page<Product> products(Integer limit, int i) {
        Pageable pageable = new PageRequest(limit, i);
        Specification<Product> spec = new Specification<Product>() {
            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> pflag = root.get("pflag");
                javax.persistence.criteria.Predicate name = criteriaBuilder.equal(pflag, "0");
                return name;
            }        //查询条件构造
        };
        return productDao.findAll(spec, pageable);
    }

    @Override
    public Product getProductById(String pid) {
        return productDao.getProductById(pid);
    }

    @Override
    public void update(Product product) {
        productDao.saveAndFlush(product);
    }

    @Override
    public void delete(String uid) {
        productDao.deleteProduct(uid);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }


}
