package com.ming.sell.mapper;

import com.ming.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster>findOrderMasterByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
