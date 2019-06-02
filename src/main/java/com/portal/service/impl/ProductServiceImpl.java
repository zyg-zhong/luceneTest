package com.portal.service.impl;

import java.util.List;

import com.portal.dao.IndexDao;
import com.portal.dao.ProductDao;
import com.portal.pojo.Products;
import com.portal.search.ResultModel;
import com.portal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商品管理服务实现类
 *
 * @version 1.0.0
 * @author allen-zyg.
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 数据库管理dao.
     */
    @Autowired
    private ProductDao productDao;

    /**
     * 索引管理dao.
     */
    @Autowired
    private IndexDao indexDao;

    /**
     * 创建索引.
     */
    @Override
    public void createIndexDump() {
        //查询数据库获得全部商品
        List<Products> proList = productDao.selectAll();
        //创建商品的全文索引
        indexDao.createDump(proList);
    }

    /**
     * 搜索列表.
     *
     * @param queryString 关键字.
     * @param catalogName id.
     * @param price       价格.
     * @param page        当前页.
     * @param sort        分类.
     * @return ResultModel
     */
    @Override
    public ResultModel getProductList(String queryString, String catalogName, String price, Integer page, String sort) {
        //解析价格区间
        Double priceStart = null;
        Double priceEnd = null;
        if (price != null && !"".equals(price)) {
            String[] strPrice = price.split("-");
            //0-79 80-199
            if (strPrice.length == 2) {
                priceStart = new Double(strPrice[0]);
                priceEnd = new Double(strPrice[1]);
                //200以上
            } else if (strPrice.length == 1) {
                priceStart = new Double(strPrice[0]);
                //设定一个很大的数值
                priceEnd = new Double(999999);
            }
        }
        ResultModel resultModel = indexDao.getProductList(queryString, catalogName, priceStart, priceEnd, page, sort);
        return resultModel;
    }


}
