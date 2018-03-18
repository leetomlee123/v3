package com.core.service;


import com.core.dao.BaseMapper;
import com.lx.dao.*;
import com.lx.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    protected BaseMapper<T> baseMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected CategoryMapper categoryMapper;
    @Autowired
    protected ProductMapper productMapper;
    @Autowired
    protected OrdersMapper ordersMapper;
    @Autowired
    protected OrderItemMapper orderItemMapper;


    @PostConstruct//在构造方法后，初化前执行
    private void initBaseMapper() throws Exception {

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();

        Class clazz = (Class) type.getActualTypeArguments()[0];

        String localField = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1) + "Mapper";

        Field field = this.getClass().getSuperclass().getDeclaredField(localField);

        Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");

        baseField.set(this, field.get(this));

    }

    public int insert(T entity) throws Exception {
        return baseMapper.insert(entity);
    }

    public int update(T entity) throws Exception {
        return baseMapper.update(entity);
    }

    public int delete(T entity) throws Exception {
        return baseMapper.delete(entity);
    }

    public int deleteList(String[] pks) throws Exception {
        return baseMapper.deleteList(pks);
    }

    public List<T> select(T entity) {
        return baseMapper.select(entity);
    }


    public List<T> select() {
        return baseMapper.select();
    }


    public Page<T> selectPage(Page<T> page) {
        page.setList(baseMapper.selectPageList(page));
        page.setTotalCount(baseMapper.selectCount(page));
        return page;
    }

    public Page<T> selectPageUseDyc(Page<T> page) {
        page.setList(baseMapper.selectPageListUseDyc(page));
        page.setTotalCount(baseMapper.selectCountUseDyc(page));
        return page;
    }
}
