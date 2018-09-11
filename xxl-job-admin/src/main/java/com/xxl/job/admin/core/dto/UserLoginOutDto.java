package com.xxl.job.admin.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-05 16:59
 */
@Setter
@Getter
public class UserLoginOutDto extends CommonMessage implements Serializable {
    private String userName;
    private String avatar;
    private String phone;
    private String realName;
}
