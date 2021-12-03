package com.jfeng.springboot.config;

import com.jfeng.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JFeng
 * @date 2021/12/2
 */

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:D://BookPic/images/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  //所有请求都被拦截，包括静态资源
                .excludePathPatterns("/", "/*.do", "/assets/**", "/bootstrap-3.4.1-dist/**", "/css/**", "/images/**", "/js/**", "/white/**", "/favicon.ico");  //放行的请求
    }
}
