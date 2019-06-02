package com.portal.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    /**商品编号.*/
    private int pid;
    /**商品名称.*/
    private String name;
    /**商品分类.*/
    private int catalog;
    /**商品分类名称.*/
    private String catalogName;
   /**价格.*/
    private double price;
    /**数量.*/
    private int number;
    /**商品描述.*/
    private String description;
    /**图片名称.*/
    private String picture;
    /**上架时间*/
    @JSONField(format = "yyyy-MM-dd")//ajax输出
    @DateTimeFormat(pattern = "yyyy-MM-dd")//输入
    private Date releaseTime;
}
