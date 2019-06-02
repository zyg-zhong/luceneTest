package com.cms.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "c_article")
public class Article {//文章
	/**
	 * 分类id.
	 */
	private int articleId;
	/**
	 * 标题.
	 */
	private String title;
	/**
	 * 发布时间.
	 */
	private Date publishDate;
	/**
	 * 作者.
	 */
	private String author;
	/**
	 * 内容.
	 */
	private String content;
	/**
	 * 上师id.
	 */
	private int masterId;

}
