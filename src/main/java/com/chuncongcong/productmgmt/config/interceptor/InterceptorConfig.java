package com.chuncongcong.productmgmt.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author HU
 * @date 2019/12/19 17:06
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/api/user/**")
            .excludePathPatterns("/test");
    }*/

    @Bean
    public AutoFillInterceptor auditingInterceptor() {
        return new AutoFillInterceptor();
    }
}
