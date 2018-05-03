package com.ming.sell.mapper;

import com.ming.sell.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {


    @Autowired
    private ProductCategoryDao productCategoryDao;

//    查询单条记录
    @Test

    public void findOnetest(){
        ProductCategory one = productCategoryDao.findOne(1);
        System.out.println(one.toString());
    }

//    增加
    @Test
    @Transactional //只供测试，数据不会写入数据库
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("测试5",10);
//        productCategory.setCategoryName("测试2");
//        productCategory.setCategoryType(1);
        ProductCategory result = productCategoryDao.save(productCategory);
        Assert.assertNotNull(result); //断言，判断是否为null
        System.out.println(productCategory.toString());
    }

//    改
    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory(3,"测试改",8);
//        productCategory.setCategoryId(2);
//        productCategory.setCategoryName("测试2改成测试3");
//        productCategory.setCategoryType(4);
        ProductCategory result = productCategoryDao.save(productCategory);
        System.out.println(result.toString());
    }

//    删除
    @Test
    public void deleteTest(){
        productCategoryDao.delete(3);
    }


    @Test
    @Transactional
    public void findByCategoryTypeIn(){
        List <Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}