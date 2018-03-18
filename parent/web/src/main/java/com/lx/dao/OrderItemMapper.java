package com.lx.dao;

import com.core.dao.BaseMapper;
import com.lx.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
    List<Map<String, Object>> findAllOrderItemByOid(@Param("oid") String oid);

}
