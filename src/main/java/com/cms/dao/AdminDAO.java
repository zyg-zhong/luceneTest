package com.cms.dao;

import com.cms.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * AdminDAO.
 * @author allen-zyg
 */
public interface AdminDAO {
	/**
	 * 登录查询.
	 * @param username
	 * @param password
	 * @return
	 */
	Admin selectOneAdminByNameAndPass(@Param("username") String username, @Param("password") String password);

	/**
	 * 注册.
	 * @param admin
	 */
	void insertNewAdmin(Admin admin);

	/**
	 * 修改密码.
	 * @param username username.
	 * @param  password password.
	 */
	void updateAdminsPassword(@Param("username") String username, @Param("password") String password);
}
