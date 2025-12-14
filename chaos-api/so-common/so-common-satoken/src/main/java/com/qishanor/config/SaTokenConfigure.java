package com.qishanor.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class SaTokenConfigure implements WebMvcConfigurer {

    public SaTokenConfigure() {
        System.out.println("SaTokenConfigure");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new SaInterceptor(handle-> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/**",           // CSS 文件
                        "/js/**",            // JS 文件
                        "/images/**",        // 图片资源
                        "/fonts/**",         // 字体资源
                        "/favicon.ico",      // 网站图标
                        "/admin/login",  // 登录接口（修正：去掉多余的斜杠）
                        "/admin/logout", // 登出接口
                        "/admin/code/**",// 验证码相关接口
                        "/actuator/**");     // Spring Boot Actuator
    }
}
