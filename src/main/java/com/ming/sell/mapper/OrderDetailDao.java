package com.ming.sell.mapper;

import com.ming.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {


    List<OrderDetail> findByOrderId(String orderId);
}
