package com.xxl.job.admin.core.enums;

import lombok.Getter;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-28 12:59
 */
@Getter
public enum UserTypeEnum {

    ADMIN(0, "超级管理员"),
    NORMAL_USER(1, "普通用户");

    private Integer code;
    private String msg;

    UserTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(Integer code) {
        for (UserTypeEnum typeEnum : UserTypeEnum.values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.getMsg();
            }
        }
        return null;
    }
}
