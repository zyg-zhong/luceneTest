package com.cms.service;

import com.cms.entity.Admin;

import java.util.Map;

/**
 * AdminService.
 *
 * @author allen-zyg
 * @version 1.0.0
 */
public interface AdminService {
    /**
     * 登录.
     *
     * @param username .
     * @param password .
     * @param code .
     * @return Map<String, String>
     */
    Map<String, String> queryOneAdmin(String username, String password, String code);

    /**
     * 添加新管理员.
     *
     * @param admin .
     */
    void registerNewAdmin(Admin admin);

    /**
     * 修改状态.
     *
     * @param username .
     * @param password .
     */
    void updateOneLevelByUsername(String username, String password);
}
