package com.cms.dao;

import java.util.List;
import com.cms.entity.Article;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component(value = "articleDAO")
@org.apache.ibatis.annotations.Mapper
public interface ArticleDAO extends Mapper<Article> {
	/**
	 * 查所有文章.
	 * @return List<Article>
	 */
	List<Article> selectAllArticle();
}
