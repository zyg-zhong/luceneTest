package com.portal.dao;

import com.portal.pojo.Products;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * 商品Dao
 * @author	allen-zyg.
 * @version 1.0.0
 */
@Component(value = "productDao")
@org.apache.ibatis.annotations.Mapper
public interface ProductDao extends Mapper<Products> {

}
