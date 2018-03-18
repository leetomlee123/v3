package com.lx.backstagemanagement.service.impl;

import com.lx.backstagemanagement.dao.OrderDao;
import com.lx.backstagemanagement.dao.OrderItemDao;
import com.lx.backstagemanagement.entity.OrderItem;
import com.lx.backstagemanagement.entity.Orders;
import com.lx.backstagemanagement.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderServiceImpl")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    @Qualifier(value = "orderDao")
    private OrderDao orderDao;
    @Autowired

    private OrderItemDao orderItemDao;

    @Override
    public Page<Orders> orders(Integer limit, int i) {
        Pageable pageable = new PageRequest(limit, i);
        return orderDao.findAll(pageable);
    }

    @Override
    public List<OrderItem> orderitem(String oid) {
        return orderItemDao.getOrderItemsByOid(oid);
    }
}
