package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 对/user进行拦截判断，不指明的话是对所有的请求拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
    }
}
