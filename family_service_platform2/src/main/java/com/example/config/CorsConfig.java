package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    //配置跨域规则
    private CorsConfiguration buildConfig(){
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域请求的地址,"*"代表所有地址都允许跨域请求
//        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOriginPattern("*");
        //跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        //跨域的请求方法,都允许
        corsConfiguration.addAllowedMethod("*");
        //在跨域请求的时候使用同一个session
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //配置可以访问的地址 "/**"代表对所有的访问路径可以按照buildConfig()规则进行跨域请求
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
