package com.example.easyaccount.config;

import com.example.easyaccount.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // 注入 token 拦截器
    @Autowired
    private TokenInterceptor interceptor = new TokenInterceptor();
    /**
     * 重写添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义拦截器，并拦截对应 url"
        registry.addInterceptor(interceptor)
                .addPathPatterns("/api/**") // 指定要拦截的请求
                .excludePathPatterns("/api/user/login"); // 不拦截的请求
    }
}