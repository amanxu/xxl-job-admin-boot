package com.xxl.job.admin.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 14:08
 */
@Setter
@Getter
public class UserExecutorLink implements Serializable {

    private Integer userId;
    private Integer id;
    private String appName;
    private String title;

}
