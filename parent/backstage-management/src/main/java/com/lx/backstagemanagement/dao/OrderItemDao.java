package com.lx.backstagemanagement.dao;

import com.lx.backstagemanagement.entity.OrderItem;
import com.lx.backstagemanagement.entity.OrderItemDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {
    @Query(value = "SELECT * FROM orderitem WHERE oid=:oid", nativeQuery = true)
    List<OrderItem> getOrderItemsByOid(@Param("oid") String oid);
}
