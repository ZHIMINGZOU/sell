package com.ming.sell.service.impl;

import com.ming.sell.dto.CarDTO;
import com.ming.sell.enums.ProductStatusEnum;
import com.ming.sell.enums.ResultEnum;
import com.ming.sell.exception.SellException;
import com.ming.sell.mapper.ProductInfoDao;
import com.ming.sell.pojo.ProductInfo;
import com.ming.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.Up.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CarDTO> carDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDTO> carDTOList) {
        for (CarDTO carDTO : carDTOList) {
            ProductInfo productInfo = productInfoDao.findOne(carDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - carDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_error);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }

    }
}
