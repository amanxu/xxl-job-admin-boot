package com.xxl.job.admin.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class XxlJobUser implements Serializable {

    private Integer id;

    private String userName;

    private String password;

    private String realName;

    private String phone;

    private Integer userType;

    private Date createTime;

    private Date updateTime;
}
