package com.lx.backstagemanagement.dao;

import com.lx.backstagemanagement.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Orders, Long> {
}
