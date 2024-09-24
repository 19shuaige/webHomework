package com.itqiang;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public static final List<String> Public_resource= Arrays.asList(  ".css", ".js", ".jpg", ".png", ".gif", ".ico", ".html", ".jsp");
    public static final List<String> Public_parth= Arrays.asList(   "/login", "/register", "/public");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("过滤器执行了");

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        //判断是否访问登陆界面以及公共资源
        boolean isExist=false;
        for (String s : Public_resource) {
            if (requestURI.endsWith(s)){
                isExist=true;

            }
        }
        for (String s : Public_parth) {
            if (requestURI.contains(s)){
                isExist=true;

            }
        }


        //是就放行
        if(isExist){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //不是则需要获得session中user属性判断是否登录
        Integer user = (Integer) request.getSession().getAttribute("user");

        //如果没有则重定向到登陆界面
        if(user==null){
            //重定向会给剩余代码执行完再重定向到其他请求
            httpServletResponse.sendRedirect("./index.html");
            return;
        }

        //有就放行
        filterChain.doFilter(servletRequest,servletResponse);

    }

}
