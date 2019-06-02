package com.cms.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Table(name = "c_menu")
public class Menu {
	@Id
	@Column(name = "menu_id")
	private Integer menuId;
	@Column(name = "menu_name")
	private String menuName;
	@Column(name = "level")
	private Integer level;
	@Column(name = "parent_id")
	private Integer parentId;
	@Column(name = "href")
	private String href;
	@Column(name = "iconCls")
	private String iconCls;
}
