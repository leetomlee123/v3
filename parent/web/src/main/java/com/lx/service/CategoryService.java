package com.lx.service;


import com.core.service.BaseServiceImpl;
import com.lx.entity.Category;
import org.springframework.stereotype.Service;

@Service(value = "categoryService")
public class CategoryService extends BaseServiceImpl<Category> implements ICategoryService {


}
