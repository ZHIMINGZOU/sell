package com.ming.sell.mapper;


import com.ming.sell.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final  String OPENID="957817315";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("17724221763");
        orderMaster.setBuyerAddress("广州");
        orderMaster.setBuyerOpenid("957817315");
        orderMaster.setOrderAmount(new BigDecimal(5));
        OrderMaster orderMaster1 = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(orderMaster1);
    }

    @Test
    public void findByBuyerOpenid() {

        PageRequest request = new PageRequest(0,5);
        Page<OrderMaster>orderMasterByBuyerOpenid= orderMasterDao.findOrderMasterByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,orderMasterByBuyerOpenid.getTotalElements());
    }
}