package com.portal.controller;

import com.portal.search.ResultModel;
import com.portal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商品管理Controller
 *
 * @version 1.0.0
 * @author allen-zyg
 */
@Controller
public class ProductController {
    /**
     * 注入service.
     */
    @Autowired
    private ProductService productService;

    /**
     * 显示后台管理页面.
     *
     * @return String .
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdmin() {
        return "portal/jsp/admin";
    }

    /**
     * 创建索引库
     *
     * @param model .
     * @return String
     */
    @RequestMapping(value = "/createIndex", method = RequestMethod.GET)
    public String createIndex(Model model) {
        productService.createIndexDump();
        model.addAttribute("result", "索引库已经成功创建");
        return "portal/jsp/admin";
    }

    /**
     * 全文检索.
     * @param queryString 关键字.
     * @param  catalogName 分类.
     * @param price 价格.
     * @param page 请求页.
     * @param sort sort 分类.
     * @param model .
     * @return String
     */
    @RequestMapping(value = "search/list", method = RequestMethod.GET)
    public String showList(String queryString, String catalogName, String price, Integer page, String sort, Model model) {
        //如果不指定page默认为第一页
        if (page == null) {
            page = 1;
        }
        ResultModel proList = null;
        //根据分类查询分类列表
        proList = productService.getProductList(queryString, catalogName, price, page, sort);

        //回传分类id
        model.addAttribute("queryString", queryString);
        model.addAttribute("catalog_name", catalogName);
        model.addAttribute("price", price);
        model.addAttribute("sort", sort);
        model.addAttribute("pageCount", proList.getPageCount());
        model.addAttribute("curPage", proList.getCurPage());
        model.addAttribute("recordCount", proList.getRecordCount());
        //回传商品列表
        model.addAttribute("list", proList.getProductList());

        return "portal/jsp/product_list";
    }

}

