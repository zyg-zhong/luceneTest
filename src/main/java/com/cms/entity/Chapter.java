package com.cms.entity;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "c_chapter")
public class Chapter {
	private String id;
	private String title; 
	private String size;
	private String src;
	private String clbumId;

}
