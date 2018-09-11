package com.xxl.job.admin;

import com.xxl.job.admin.filter.AuthJwtFilter;
import org.assertj.core.util.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-20 15:18
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.xxl.job.admin.dao")
@ComponentScan(value = {"com.xxl.job.admin"})
public class AdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdminApplication.class);
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthJwtFilter());
        //添加需要拦截的url
        List<String> urlPatterns = Lists.newArrayList();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

}
