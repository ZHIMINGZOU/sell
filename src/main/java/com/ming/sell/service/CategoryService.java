package com.ming.sell.service;

import com.ming.sell.pojo.ProductCategory;

import java.util.List;

public interface CategoryService {


    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> type);

    ProductCategory save(ProductCategory productCategory);
}
