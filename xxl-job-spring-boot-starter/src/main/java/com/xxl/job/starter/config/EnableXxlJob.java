package com.xxl.job.starter.config;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(XxlJobAutoConfig.class)
public @interface EnableXxlJob {
}
