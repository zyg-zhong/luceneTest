package com.cms.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cms.dao.AdminDAO;
import com.cms.entity.Admin;
import com.cms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * adminService.
 *
 * @author ALLEN-ZYG
 * @version 1.0.0
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    /**
     * 创建dao层对象.
     */
    @Autowired
    private AdminDAO adminDAO;

    /**
     * 登录.
     *
     * @param username .
     * @param password .
     * @param code     .
     * @return Map<String       ,       String>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Map<String, String> queryOneAdmin(String username, String password, String code) {
        //创建session对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(); //创建session对象
        Map<String, String> map = new HashMap<String, String>();
        //接收数据
        String syscode = (String) session.getAttribute("code");
        //System.out.println("code:"+code+"syscode"+syscode);
        //判断验证码是否正确
        if (code.equals(syscode)) {
            //调用业务
            Admin admin = adminDAO.selectOneAdminByNameAndPass(username, password);
            //判断真假
            if (admin != null) {
                map.put("message", "登录成功！");
                session.setAttribute("admin", admin);
                return map;
            } else {
                map.put("message", "账号或密码错误！");
                return map;
            }
        } else {
            map.put("message", "验证码错误！");
            return map;
        }
    }

    /**
     * 添加新管理员.
     * @param admin .
     */
    @Override
    @Transactional
    public void registerNewAdmin(Admin admin) {
        adminDAO.insertNewAdmin(admin);

    }

    /**
     * 修改状态.
     * @param username .
     * @param password .
     */
    @Override
    @Transactional
    public void updateOneLevelByUsername(String username, String password) {
        adminDAO.updateAdminsPassword(username, password);
    }

}
