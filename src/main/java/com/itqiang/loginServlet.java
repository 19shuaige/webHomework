package com.itqiang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet {

    public static final String Username="zhangsan";
    public static final String Password="123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //比对账号密码
        Map<String, String[]> parameterMap = req.getParameterMap();
        String username = parameterMap.get("username")[0];
        String password = parameterMap.get("password")[0];

        //成功在session中设置user属性
        if(username.equals(Username)&&password.equals(Password)){
            System.out.println("登陆成功");
            HttpSession session = req.getSession();
            session.setAttribute("user",1);
            resp.sendRedirect("./index1.html");

        }else {
            System.out.println("登录失败");
            resp.sendRedirect("./index.html");
        }

    }
}
