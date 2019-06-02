package com.cms.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Table;

@Data
@Table(name = "c_carousel")
public class Carousel {
	private int carouselId;
	private String carouselName;
	private String imgPath;
	private String description;
	private int status;
	@JSONField(format="yyyy-MM-dd")//ajax输出
	@DateTimeFormat(pattern="yyyy-MM-dd")//输入
	private Date uploadDate;
	
}
