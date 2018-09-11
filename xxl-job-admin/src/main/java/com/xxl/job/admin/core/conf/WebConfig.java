package com.xxl.job.admin.core.conf;

import com.xxl.job.admin.controller.resolver.WebExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-20 17:56
 */
@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * @description:
     * @author cheng
     * @dateTime 2018/4/19 17:59
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("init static resource local");
        // 和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**/*html").addResourceLocations("/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
    }

    /**
     * 异常处理
     *
     * @return
     */
    @Bean
    public WebExceptionResolver exceptionResolver() {
        WebExceptionResolver exceptionResolver = new WebExceptionResolver();
        return exceptionResolver;
    }

}