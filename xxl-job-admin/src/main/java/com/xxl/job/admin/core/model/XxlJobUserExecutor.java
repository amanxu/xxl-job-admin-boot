package com.xxl.job.admin.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-28 13:13
 */
@Setter
@Getter
@ToString
public class XxlJobUserExecutor implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer executorId;
}
