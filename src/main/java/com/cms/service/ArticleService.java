package com.cms.service;

import java.util.List;

import com.cms.entity.Article;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
public interface ArticleService {
	//查所有文章

	/**
	 * 查所有文章列表.
	 * @return List<Article>
	 */
	List<Article> queryAlllArticles();



	/**
	 * 添加文章.
	 * @param ids id数组
	 * @param article  article文章实体
	 * @return  String
	 */
	String addArtic(Integer[] ids, Article article);
}
