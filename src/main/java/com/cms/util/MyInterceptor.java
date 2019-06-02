package com.cms.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cms.entity.Admin;

/**
 * @author allen-zyg
 * @version 1.0.0
 */
public class MyInterceptor implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Admin admin = (Admin) session.getAttribute("admin");
        //判断
        if (admin == null) {
            //为空---》返回登录页面
            res.sendRedirect("http://localhost:8989/cmfz_zyg/login.jsp");
        } else {
            //不为空-----》放行
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }


}
