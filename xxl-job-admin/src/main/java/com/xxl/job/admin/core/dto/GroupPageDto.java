package com.xxl.job.admin.core.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 12:15
 */
@Setter
@Getter
public class GroupPageDto implements Serializable {

    private String appName;

    private String title;

    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNo;

    @Min(value = 1, message = "每页尺寸不能小于1")
    private Integer pageSize;
}
