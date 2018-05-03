package com.ming.sell.service.impl;

import com.ming.sell.dto.CarDTO;
import com.ming.sell.dto.OrderDTO;
import com.ming.sell.enums.OrderStatusEnum;
import com.ming.sell.enums.PayStatusEnum;
import com.ming.sell.enums.ResultEnum;
import com.ming.sell.exception.SellException;
import com.ming.sell.mapper.OrderDetailDao;
import com.ming.sell.mapper.OrderMasterDao;
import com.ming.sell.pojo.OrderDetail;
import com.ming.sell.pojo.OrderMaster;
import com.ming.sell.pojo.ProductInfo;
import com.ming.sell.service.OrderService;
import com.ming.sell.service.ProductService;
import com.ming.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    OrderMasterDao orderMasterDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        //BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        BigDecimal orderAmount = new BigDecimal(0); //初始化总价
        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价（计算一件商品总价，因为for循环所以累积计算订单总价）
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);//设置orderDetail的productName和Icon
            orderDetailDao.save(orderDetail);//保存到订单详情
            //扣库存
            List<CarDTO> carDTOList = new ArrayList<>();
            CarDTO carDTO = new CarDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            carDTOList.add(carDTO);//因为decreaseStock是List
            productService.decreaseStock(carDTOList);
        }

        //写入订单数据库（orderMaster、orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.wait.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.save(orderMaster);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(OrderDTO orderId) {
        return null;
    }

    @Override
    public OrderDTO findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
