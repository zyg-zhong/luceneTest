package com.cms.controller;

import java.util.List;

import com.cms.entity.Menu;
import com.cms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	//创建service 对象
	@Autowired
	private MenuService ms;
	
	//查所有
	@RequestMapping(value = "/queryAllMenus", method = RequestMethod.POST)
	@ResponseBody
	public List<Menu> queryAllMenus(){
		List<Menu> MenuList = ms.queryMenuList();
		return MenuList;
	}
	//查子菜单
	@RequestMapping("/queryOneMenu")
	@ResponseBody
	public Menu queryOneMenu(int parentId){
		System.out.println("id:"+parentId);
		Menu menu = ms.queryOneMenuByParentId(parentId);
		System.out.println("打印："+menu);
		return menu;
	}
}
