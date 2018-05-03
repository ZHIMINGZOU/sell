package com.ming.sell.service.impl;

import com.ming.sell.mapper.ProductCategoryDao;
import com.ming.sell.pojo.ProductCategory;
import com.ming.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryDao.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> type) {
        return productCategoryDao.findByCategoryTypeIn(type);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
