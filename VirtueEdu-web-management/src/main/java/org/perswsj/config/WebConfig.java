package org.perswsj.config;

import org.perswsj.Interceptor.LoginInterCeptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterCeptor loginInterCeptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册登录拦截器
//        registry.addInterceptor(loginInterCeptor)
//                .addPathPatterns("/**") // 拦截所有路径
//                .excludePathPatterns("/login/**"); // 排除登录接口
//    }
}
