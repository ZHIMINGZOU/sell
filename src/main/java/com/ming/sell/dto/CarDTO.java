package com.ming.sell.dto;


import lombok.Data;

@Data
public class CarDTO {

    //商品Id
    private String productId;

    //商品数量
    private Integer productQuantity;

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
