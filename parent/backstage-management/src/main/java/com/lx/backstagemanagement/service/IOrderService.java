package com.lx.backstagemanagement.service;


import com.lx.backstagemanagement.entity.OrderItem;
import com.lx.backstagemanagement.entity.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {


    Page<Orders> orders(Integer limit, int i);

    List<OrderItem> orderitem(String oid);
}
