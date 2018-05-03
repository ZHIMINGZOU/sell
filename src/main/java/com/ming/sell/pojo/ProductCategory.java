package com.ming.sell.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate  //动态更新时间
@Data
public class ProductCategory {

//    类目表
    @Id  //主键
    @GeneratedValue  //自增类型
    private Integer categoryId;

//    类目名
    private String categoryName;

//    类目编号
    private  Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory (String name,Integer type){
        this.categoryName = name;
        this.categoryType = type;
    }
    public ProductCategory(int id,String name,Integer Type){
        this.categoryId= id;
        this.categoryName = name;
        this.categoryType=Type;
    }
    public ProductCategory(){

    }

}