package com.portal.dao;
import java.util.List;
import com.portal.pojo.Products;
import com.portal.search.ResultModel;
/**
 * 索引管理Dao.
 * @author allen-zyg .
 * @version 1.0.0
 */
public interface IndexDao {
    /**
	 * 创建索引库.
	 * @param list .
	 */
	void createDump(List<Products> list);
	/**
	 * 搜索.
	 * @param queryString .
	 * @param catalogName .
	 * @param priceStart .
	 * @param priceEnd .
	 * @param page .
	 * @param sort .
	 * @return ResultModel .
	 */
	ResultModel getProductList(String queryString,
                               String catalogName, Double priceStart,
                               Double priceEnd, Integer page, String sort);
}
