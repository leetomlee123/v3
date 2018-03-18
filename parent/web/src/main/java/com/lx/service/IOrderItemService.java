package com.lx.service;

import com.lx.entity.OrderItemDetailResponse;
import com.lx.entity.Product;

import java.util.List;
import java.util.Map;

public interface IOrderItemService {
    List<Map<String, Object>> findAllOrderItemByOid(String oid);

    List<OrderItemDetailResponse> findProductInfoByOid(String oid);
}
