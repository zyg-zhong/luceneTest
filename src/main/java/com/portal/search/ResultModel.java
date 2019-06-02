package com.portal.search;

import com.portal.pojo.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询商品列表返回的结果.
 *
 * @version 1.0.0
 * @author allen-zyg.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel {

    /**
     * 商品列表.
     */
    private List<Products> productList;
    /**
     * 商品总数.
     */
    private int recordCount;
    /**
     * 总页数.
     */
    private int pageCount;
    /**
     * 当前页.
     */
    private int curPage;

}
