package com.cms.service;

import java.util.List;
import java.util.Map;

import com.cms.entity.CMonth;
import com.cms.entity.User;

/**
 * UserService.
 */
public interface UserService {
    /**
     * 查询所有.
     *
     * @return List<User>
     */
    List<User> queryAllUsers();

    /**
     * 查询部分.
     *
     * @param id .
     * @return List<User>
     */
    List<User> querySomeUserByIds(Integer id);

    /**
     * 获得没月份女生注册人数.
     *
     * @return List<CMonth>
     */
    List<CMonth> queryNvMonth();

    /**
     * 获得没月份男生注册人数.
     *
     * @return List<CMonth>
     */
    List<CMonth> queryNanMonth();

    /**
     * 按省份统计分布.
     *
     * @return Map <String , Object>
     */
    Map<String, Object> queryprovinceCountService();
}
