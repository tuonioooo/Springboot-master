package com.master.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-8
 * Time: 10:29
 * info:
 */
@Component
@Order(2)
public class HomeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("HomeInterceptor自定义拦截器 = preHandle");

//        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.print("拦截成功!!!");
//        writer.flush();
//        writer.close();

//        response.sendRedirect("/404");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("HomeInterceptor自定义拦截器 = postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("HomeInterceptor自定义拦截器 = afterCompletion");
    }
}
