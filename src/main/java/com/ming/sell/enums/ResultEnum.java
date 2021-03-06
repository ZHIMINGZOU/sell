package com.ming.sell.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_error(11,"库存不足");
    private Integer code;
    private String message;


    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;

    }
}
