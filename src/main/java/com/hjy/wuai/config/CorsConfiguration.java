package com.hjy.wuai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hjy
 * @date 2021/3/3 0003,下午 18:59
 * @email 541605007@qq.com
 * <p>
 * 全局跨域配置类
 * <p>
 * addMapping：设置允许跨域的请求路径
 * allowedOrigins：允许XX域名的请求来源，可以跨域访问当前服务器的接口
 * allowedMethods: 允许请求的方法
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }
}
