package com.cms.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Table;

@Data
@Table(name = "c_counter")
public class Counter {
	private int counter_id;
	private String counteName;
	private int count;
	@JSONField(format="yyyy-MM-dd")//ajax输出
	@DateTimeFormat(pattern="yyyy-MM-dd")//输入
	private Date createDate;
	@JSONField(format="yyyy-MM-dd")//ajax输出
	@DateTimeFormat(pattern="yyyy-MM-dd")//输入
	private Date updatDate;
	private int courseId;
	private int userId;

	
}
