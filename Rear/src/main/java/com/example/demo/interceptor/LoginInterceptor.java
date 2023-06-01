package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器需要实现HandlerInterceptor的接口，这些接口有默认的实现方式了，所以不用必须实现
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws  Exception{
        System.out.println("From the LoginIntercepter");
        return true;
    }
}
