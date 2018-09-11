package com.xxl.job.admin.core.conf;

import com.xxl.job.admin.core.schedule.XxlJobDynamicScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-20 18:26
 */
@Configuration
public class SchedulerConfig {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private XxlJobAdminConfig xxlJobAdminConfig;

    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        // 自动启动
        schedulerFactoryBean.setAutoStartup(true);
        // 延时启动，应用启动成功后在启动
        schedulerFactoryBean.setStartupDelay(20);
        // 覆盖DB中JOB：true、以数据库中已经存在的为准：false
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        return schedulerFactoryBean;
    }

    @Bean(name = "xxlJobDynamicScheduler", initMethod = "init", destroyMethod = "destroy")
    public XxlJobDynamicScheduler dynamicScheduler() {
        XxlJobDynamicScheduler dynamicScheduler = new XxlJobDynamicScheduler();
        dynamicScheduler.setScheduler(schedulerFactoryBean().getScheduler());
        dynamicScheduler.setAccessToken(xxlJobAdminConfig.getAccessToken());
        return dynamicScheduler;
    }
}
