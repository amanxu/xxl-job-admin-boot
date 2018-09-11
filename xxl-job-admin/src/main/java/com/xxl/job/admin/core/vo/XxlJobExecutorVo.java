package com.xxl.job.admin.core.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 13:17
 */
@Setter
@Getter
public class XxlJobExecutorVo implements Serializable {

    private int id;
    private String appName;
    private String title;
}
