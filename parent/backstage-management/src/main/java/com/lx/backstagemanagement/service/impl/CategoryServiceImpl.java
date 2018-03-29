package com.lx.backstagemanagement.service.impl;

import com.lx.backstagemanagement.dao.CategoryDao;
import com.lx.backstagemanagement.entity.Category;
import com.lx.backstagemanagement.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "categoryServiceImpl")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    @Qualifier(value = "categoryDao")
    private CategoryDao categoryDao;




    @Override
    public List<Category> categorys() {
        return categoryDao.findAll();
    }

    @Override
    public void edit(Category category) {
        categoryDao.saveAndFlush(category);
    }

    @Override
    public Category getByCid(String cid) {


        return categoryDao.findByCid(cid);
    }

    @Override
    public void deleteCategory(String cid) {
        Category category = new Category();
        category.setCid(cid);
        int i=0;

        categoryDao.delete(category);

    }

    @Override
    public void add(Category category) {

        categoryDao.save(category);
    }
}
