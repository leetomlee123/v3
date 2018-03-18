package com.lx.service;

import com.core.service.BaseServiceImpl;
import com.lx.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "orderService")
public class OrderService extends BaseServiceImpl<Orders> implements IOrderService {
    @Override
    public List<Orders> findAllOrders(String uid) {
        return ordersMapper.findAllOrders(uid);
    }

    @Override
    public void submitOrder(Orders orders) {
        ordersMapper.submitOrder(orders);
        orders.getOrderItems().stream().forEach(entry -> orderItemMapper.insert(entry));
    }

    @Override
    public void payOrder(Orders orders) {
        ordersMapper.payOrder(orders);
    }


}
