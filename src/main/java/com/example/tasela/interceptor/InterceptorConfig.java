package com.example.tasela.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jinmos
 * @date 2019-09-16 16:04
 * Token拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @TokenLogin 注解 决定是否需要登录
              registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**") .excludePathPatterns("/","/login","/insertUser");
      }
     @Bean
     public TaselaInterceptor authenticationInterceptor() {
                 return new TaselaInterceptor();
    }
}
