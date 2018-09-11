package com.xxl.job.starter.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.starter.properties.XxlJobProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(XxlJobExecutor.class)
@ConditionalOnProperty(value = "xxl.job.enabled", matchIfMissing = true)
@EnableConfigurationProperties(XxlJobProperties.class)
@Slf4j
public class XxlJobAutoConfig {


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor(XxlJobProperties properties,
                                         @Value("${spring.application.name}")
                                                 String appName) {
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        BeanUtils.copyProperties(properties, xxlJobExecutor);
        xxlJobExecutor.setAppName(appName);
        return xxlJobExecutor;
    }
}
