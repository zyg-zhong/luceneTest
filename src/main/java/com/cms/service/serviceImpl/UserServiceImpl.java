package com.cms.service.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.dao.UserDAO;
import com.cms.entity.CMonth;
import com.cms.entity.ProvinceCount;
import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * 创建dao对象.
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * queryAllUsers.
     * @return List<User>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> queryAllUsers() {
        return userDAO.selectAllUser();
    }

    //查一个
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> querySomeUserByIds(Integer id) {
        List<User> list = new ArrayList<User>();
        list.add(userDAO.selectSomeUser(id));
        return list;
    }

    /**
     * 获得女生每月注册人数.
     * @return List<CMonth>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CMonth> queryNvMonth() {
        List<CMonth> nv = userDAO.getNvCount();
        //所有月份都展示
        for (int i = 1; i < 13; i++) {
            if (!nv.contains(new CMonth(i, 0))) {
                nv.add(new CMonth(i, 0));
            }
        }
        //去除月属性相同的元素
        for (int k = 0; k < nv.size() - 1; k++) {
            for (int j = nv.size() - 1; j > k; j--) {
                if (nv.get(j).getMonth() == nv.get(k).getMonth()) {
                    nv.remove(j);
                }
            }
        }
        //排序
        nv.sort(new Comparator<CMonth>() { //Comparator 比较器. 需要实现比较方法
            @Override
            public int compare(CMonth m1, CMonth m2) {
                return m1.getMonth() - m2.getMonth();
            }
        });
        return nv;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CMonth> queryNanMonth() {
        List<CMonth> nan = userDAO.getNanCount();
        //所有月份都展示
        for (int i = 1; i < 13; i++) {
            if (!nan.contains(new CMonth(i, 0))) {
                nan.add(new CMonth(i, 0));
            }
        }
        //去除月属性相同的元素
        for (int i = 0; i < nan.size() - 1; i++) {
            for (int j = nan.size() - 1; j > i; j--) {
                if (nan.get(j).getMonth() == nan.get(i).getMonth()) {
                    nan.remove(j);
                }
            }
        }
        //排序
        nan.sort(new Comparator<CMonth>() { //Comparator 比较器. 需要实现比较方法
            @Override
            public int compare(CMonth m1, CMonth m2) {
                return m1.getMonth() - m2.getMonth();
            }
        });
        return nan;
    }

    //按省份查询--统计
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryprovinceCountService() {
        //创建map
        Map<String, Object> map = new HashMap<String, Object>();
        List<ProvinceCount> countN = userDAO.queryCountN(); //男
        List<ProvinceCount> countV = userDAO.queryCountV(); //女
        map.put("countN", countN);
        map.put("countV", countV);
        return map;
    }

}
