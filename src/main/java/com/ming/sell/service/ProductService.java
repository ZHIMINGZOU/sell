package com.ming.sell.service;


import com.ming.sell.dto.CarDTO;
import com.ming.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    //    查询所有在架商品列表
    List<ProductInfo> findUpAll();

    //    查询所有商品列表 同时提供分页
    Page<ProductInfo> findAll(Pageable pageable);

    //    保存
    ProductInfo save(ProductInfo productInfo);

    //    加库存(根据productId加减)
    void increaseStock(List<CarDTO> carDTOList);

    //    减库存
    void decreaseStock(List<CarDTO> carDTOList);

}
