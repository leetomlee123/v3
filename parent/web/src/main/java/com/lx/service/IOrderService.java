package com.lx.service;

import com.core.service.BaseService;
import com.lx.entity.Orders;

import java.util.List;
import java.util.Map;

public interface IOrderService extends BaseService<Orders> {
    List<Orders> findAllOrders(String uid);

    void submitOrder(Orders orders);


    void payOrder(Orders orders);
}
