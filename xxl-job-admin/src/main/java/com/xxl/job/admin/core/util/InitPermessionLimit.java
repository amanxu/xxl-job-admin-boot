package com.xxl.job.admin.core.util;

import com.xxl.job.admin.controller.annotation.PermessionLimit;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Lists;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @description: 获取controller中PermessionLimit注解的方法的路径(controller)上必须加注解RequestMapping
 * @author: xiaoxu.nie
 * @date: 2018-09-08 15:19
 */
@Component
public class InitPermessionLimit implements ApplicationListener<ContextRefreshedEvent> {

    public static List<String> limitUrl = Lists.newArrayList();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> beansWithAnnotation = event.getApplicationContext().getBeansWithAnnotation(RequestMapping.class);

        beansWithAnnotation.values().stream().forEach(bean -> {
            Method[] methods = bean.getClass().getMethods();
            if (ArrayUtils.isEmpty(methods)) {
                return;
            }
            Lists.newArrayList(methods).stream().forEach(method -> {
                if (method.isAnnotationPresent(PermessionLimit.class)) {
                    if (method.isAnnotationPresent(PostMapping.class)) {
                        limitUrl.addAll(Lists.newArrayList(method.getAnnotation(PostMapping.class).value()));
                    } else if (method.isAnnotationPresent(GetMapping.class)) {
                        limitUrl.addAll(Lists.newArrayList(method.getAnnotation(GetMapping.class).value()));
                    } else if (method.isAnnotationPresent(RequestMapping.class)) {
                        limitUrl.addAll(Lists.newArrayList(method.getAnnotation(RequestMapping.class).value()));
                    }
                }
            });
        });
    }
}
