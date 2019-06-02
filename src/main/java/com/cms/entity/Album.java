package com.cms.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Table;

@Data
@Table(name = "c_album")
public class Album {
	private String id; 
	private String title; 
	private String coverPath;
	private int count;
	private int score; 
	private String author;
	private String teller;
	@JSONField(format="yyyy-MM-dd")//ajax输出
	@DateTimeFormat(pattern="yyyy-MM-dd")//输入
	private Date publish_date;
	private String brief;
	private List<Chapter> children;
	
}
