package com.lx.service;

import com.core.service.BaseServiceImpl;
import com.lx.entity.OrderItem;
import com.lx.entity.OrderItemDetailResponse;
import com.lx.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "orderItemService")
public class OrderItemService extends BaseServiceImpl<OrderItem> implements IOrderItemService {
    @Override
    public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
        return orderItemMapper.findAllOrderItemByOid(oid);
    }

    @Override
    public List<OrderItemDetailResponse> findProductInfoByOid(String oid) {
        return ordersMapper.findProductInfoByOid(oid);
    }
}
