package com.xinyuzang.game.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhoutao
 * @date 2020/6/3
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    TokenInterceptor authenticationInterceptor() {
        return new TokenInterceptor();
    }
}
