package com.cms.service;

import java.util.List;

import com.cms.entity.Menu;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
public interface MenuService {
	/**
	 * 查所有.
	 * @return List<Menu>
	 */
	List<Menu> queryMenuList();

	/**
	 * 通过id查询.
	 * @param id .
	 * @return Menu
	 */
	Menu queryOneMenuByParentId(int id);
}
