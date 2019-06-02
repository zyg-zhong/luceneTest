package com.cms.controller;

import com.cms.entity.Admin;
import com.cms.service.AdminService;
import com.cms.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户controller.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /***创建service对象*/
    @Autowired
    private AdminService as;

    /**
     * adminLogin.
     *
     * @param username .
     * @param code     .
     * @param password password.
     * @param session  .
     * @return Map<String   ,       String>
     */
    @RequestMapping("/adminLogin")
    @ResponseBody
    public Map<String, String> adminLogin(String username, String code, String password, HttpSession session) {
        //调用service业务
        Map<String, String> map = as.queryOneAdmin(username, password, code);
        return map;
    }

    /**
     * 获得验证码.
     *
     * @param model    .
     * @param response .
     * @param session  .
     * @return String
     * @throws IOException IOException
     */

    @RequestMapping("/getcode")
    public String getcode(Model model, HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/png");
        String code = VerifyCodeUtil.generateVerifyCode(6);
        session.setAttribute("code", code);
        BufferedImage image = VerifyCodeUtil.getImage(120, 50, code);
        ImageIO.write(image, "png", response.getOutputStream());
        return null;
    }

    /**
     * 退出系统.
     *
     * @param session .
     * @param respose .
     * @throws IOException IOException.
     */
    @RequestMapping("/exit")
    @ResponseBody
    public void exit(HttpSession session, HttpServletResponse respose) throws IOException {
        session.setAttribute("admin", null);
        respose.sendRedirect("http://localhost:8989/cmfz_zyg/login.jsp");
    }

    /**
     * 修改密码.
     *
     * @param session .
     * @param oldPass .
     * @param newpassA .
     * @return Map<String, String>
     */
    @RequestMapping("/updatePssword")
    @ResponseBody
    public Map<String, String> updatePssword(HttpSession session, String oldPass, String newpassA) {
        Admin admin = (Admin) session.getAttribute("admin");
        Map<String, String> map = new HashMap<String, String>();
        if (oldPass.equals(admin.getPassword())) {
            as.updateOneLevelByUsername(admin.getUsername(), newpassA);
            map.put("words", "修改成功");
            return map;
        } else {
            map.put("words", "原密码输入错误！请重新输入！");
            return map;
        }
    }
}
