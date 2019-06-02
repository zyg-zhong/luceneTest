package com.cms.dao;


import java.util.List;

import com.cms.entity.Menu;

public interface MenuDAO {
	//查看所有菜单
	List<Menu> selectAllMenus();
	//查二级菜单
	Menu selectClildrenManu(int id);
}
