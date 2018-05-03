package com.ming.sell.mapper;

import com.ming.sell.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(5));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("很多皮蛋 建议下单");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(0);
        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);
    }



    @Test
    public void findByProductStatus() {

        List<ProductInfo> result = productInfoDao.findByProductStatus(0);
        Assert.assertNotNull(result);
        System.out.println(result.toString());
    }
}