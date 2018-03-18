package com.lx.backstagemanagement.service;

import com.lx.backstagemanagement.entity.Category;

import java.util.List;

public interface ICategoryService {


    List<Category> categorys();

    void edit(Category category);

    Category getByCid(String cid);

    void deleteCategory(String cid);

    void add(Category category);
}
