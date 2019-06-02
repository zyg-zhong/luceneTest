package com.cms.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Course实体类.
 * @author allen-zyg
 * @version 1.0.0
 */
@Data
@Table(name = "c_course")
public class Course {
	/**
	 * course_id.
	 */
	private int course_id;
	/**
	 * title.
	 */
	private String title;
	/**
	 * status.
	 */
	private int status;
	/**
	 * iconCls.
	 */
	private String iconCls;
	/***
	 * userId.
	 */
	private int userId;

}
