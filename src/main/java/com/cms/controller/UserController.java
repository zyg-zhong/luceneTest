package com.cms.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.entity.CMonth;
import com.cms.entity.User;
import com.cms.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
@Controller
@RequestMapping("/user")
public class UserController {
	//创建service对象
	@Autowired
	private UserService us;
	
	//查所有用户
	@RequestMapping("/queryAllUser")
	@ResponseBody
	public List<User> queryAllUser(){

		return us.queryAllUsers();
	}
	//导出用户表
	@RequestMapping("/downloadUser")
	@ResponseBody
	public void downloadUser(String src,HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<User> list =us.queryAllUsers();
		String fileName="xxxxxx";//下载文件名
		
		//标准设置，之前根据老师的设置，是错的////加上这个 URLEncoder.encode(fileName + ".xls", "UTF-8"))
		response.setContentType("application/application/vnd.ms-excel");
		  response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户表"),
	            User.class, list);
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			System.out.println("出错了");
			e.printStackTrace();
		}finally{
			workbook.close();
		}
		
	}
	//自定义导出---部分
	@RequestMapping("/downloadSomeUser")
	@ResponseBody
	public void downloadSomeUser(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		System.out.println(id);
		List<User> list=us.querySomeUserByIds(id);
		String fileName=id+"信息表";//下载文件名
		//标准设置，之前根据老师的设置，是错的////加上这个 URLEncoder.encode(fileName + ".xls", "UTF-8"))
		response.setContentType("application/application/vnd.ms-excel");
		  response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户表"),
	            User.class, list);
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			workbook.close();
		}
	}
	//获得男女生每月注册人数
	@RequestMapping("/queryCount")
	@ResponseBody
	public List<List<CMonth>> queryCount(){
		//获得月份集合
		List<List<CMonth>> lists=new ArrayList<List<CMonth>>();
		lists.add(us.queryNanMonth());
		lists.add(us.queryNvMonth());
		return lists;
	}
	
	//按省份分布统计
	@RequestMapping("/queryCountByProvince")
	@ResponseBody
	public Map <String , Object> queryCountByProvince(){
		Map <String , Object> map=us.queryprovinceCountService();
		return map;
	}
	
	
	/**
	 * 导入文件---添加用户----id不能有，因为id是mysql自动生成的
	 * 本系统没有这个需求，详细看练习poi
	 */
	
}