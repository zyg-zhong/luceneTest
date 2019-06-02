package com.cms.dao;

import java.util.List;

import com.cms.entity.CMonth;
import com.cms.entity.ProvinceCount;
import com.cms.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
	//查所有用户
	List<User> selectAllUser();
	//通过数组查部分用户
	User selectSomeUser(@Param("id") Integer id);
	//统计女生每月注册人数
	List<CMonth> getNvCount();
	//统计女生每月注册人数
	List<CMonth> getNanCount();
	//统计各个省份注册人数分布---女
	List<ProvinceCount> queryCountV();
	//统计各个省份注册人数分布
	List<ProvinceCount> queryCountN();
	//导入--通过数组导入数据
	//
}
