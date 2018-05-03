package com.ming.sell.enums;


/*
 *
 * 商品状态
 * */

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    Up(0, "上架"),
    DOWN(1, "下架");;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
