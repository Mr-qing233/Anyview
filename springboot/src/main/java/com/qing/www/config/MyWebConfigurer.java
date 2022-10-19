package com.qing.www.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 跨域问题解决方案[后端]
         * 所有请求都允许跨域，使用这种配置就不需要在interceptor中配置header了
         */
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

