package com.xxl.job.admin.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaoxu.nie
 */
@Setter
@Getter
@ToString
public class XxlJobUserApiLog implements Serializable {

    private Integer id;

    private String userIp;

    private String apiParams;

    private String postBody;

    private String apiPath;

    private String loginAccount;

    private String actionDesc;

    private String apiResult;

    private Date createTime;

}
