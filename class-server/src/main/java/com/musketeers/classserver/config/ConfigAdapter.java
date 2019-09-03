package com.musketeers.classserver.config;

import com.musketeers.classserver.framework.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置 fanjiahao 20190414
 */
@Configuration
public class ConfigAdapter extends WebMvcConfigurationSupport {
    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/system/**")
                .addPathPatterns("/business/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns("/system/sys-user/login")
                .excludePathPatterns("/system/sys-user/logout")
                .excludePathPatterns("/business/bus-category/**")
                .excludePathPatterns("/business/bus-class/getAllClass")
                .excludePathPatterns("/business/bus-class/getClassByID")
                .excludePathPatterns("/business/bus-media/queryAllMedia")
                .excludePathPatterns("/business/bus-media/queryMediaById")
                .excludePathPatterns("/business/bus-news/queryAllNews")
                .excludePathPatterns("/business/bus-news/queryNewsById")
                .excludePathPatterns("/business/bus-order/createOrder");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}