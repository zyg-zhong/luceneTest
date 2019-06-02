package com.portal.service;

import com.portal.search.ResultModel;

/**
 * 商品管理服务类
 *
 * @version 1.0.0
 * @author allen-zyg .
 */
public interface ProductService {
    /**
     * 创建索引库.
     */
    void createIndexDump();

    /**
     * 查询列表.
     *
     * @param queryString .
     * @param catalogName .
     * @param price       .
     * @param page        .
     * @param sort        .
     * @return ResultModel
     */
    ResultModel getProductList(String queryString, String catalogName, String price, Integer page, String sort);

}
