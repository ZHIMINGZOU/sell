package com.ming.sell.mapper;

import com.ming.sell.pojo.OrderDetail;
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
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("1");
        orderDetail.setProductIcon("http://xx.jpg");
        orderDetail.setProductId("1");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(5));
        orderDetail.setProductQuantity(2);
        OrderDetail orderDetail1= orderDetailDao.save(orderDetail);
        Assert.assertNotNull(orderDetail1);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId("1");
        Assert.assertNotEquals(0,orderDetails.size());

    }
}