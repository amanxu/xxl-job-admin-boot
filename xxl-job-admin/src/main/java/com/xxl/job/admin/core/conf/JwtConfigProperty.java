package com.xxl.job.admin.core.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-08 10:16
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperty {

    private String base64Secret;
    private long expiresTime;
    /**
     * 静态资源路径
     */
    private String staticPath;
}
