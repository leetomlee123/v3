package com.lx.dao;

import com.core.dao.BaseMapper;
import com.lx.entity.OrderItemDetailResponse;
import com.lx.entity.Orders;
import com.lx.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends BaseMapper<Orders> {
    List<Orders> findAllOrders(String uid);

    List<Map<String, Object>> findAllOrderItemByOid(String oid);

    void submitOrder(Orders orders);

    @Select("SELECT * FROM product a,(SELECT pid,count,subtotal FROM orderitem WHERE oid = #{oid}) b WHERE a.pid = b.pid")
    List<OrderItemDetailResponse> findProductInfoByOid(@Param("oid") String oid);

    @Update("update orders set state=1 where oid=#{orders.oid}")
    void payOrder(@Param("orders") Orders orders);
}
