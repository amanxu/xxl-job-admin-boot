package com.xxl.job.admin.core.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 11:43
 */
@Data
public class XxlJobUserVo {

    private Integer id;

    private String userName;

    private String realName;

    private String phone;

    private String password;

    private Integer userType;

    private String userTypeDesc;

    private Date createTime;

    private List<String> roles;
}
