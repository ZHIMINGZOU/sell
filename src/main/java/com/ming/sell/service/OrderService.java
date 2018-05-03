package com.ming.sell.service;

import com.ming.sell.dto.OrderDTO;
import com.ming.sell.pojo.OrderDetail;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 */

public interface OrderService {


    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(OrderDTO orderId);

    //查询订单列表
    OrderDTO findList(String buyerOpenId, Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);

}
