package com.ming.sell.service.impl;

import com.ming.sell.enums.ProductStatusEnum;
import com.ming.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo result = productService.findOne("1");
        Assert.assertNotEquals("1",result);
        System.out.println(result.toString());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = productService.findUpAll();
        Assert.assertNotNull(result);
        System.out.println(result.toString());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2); //查第0页,显示2条
        Page<ProductInfo> result = productService.findAll(request);
        System.out.println(result.getTotalElements()); //总元素
    }

    @Test
    public void save() {
        ProductInfo productInfo  = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("玉米粥");
        productInfo.setProductPrice(new BigDecimal(6));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("很多玉米 建议下单");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(ProductStatusEnum.Up.getCode());
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
        System.out.println(result.toString());
    }
}