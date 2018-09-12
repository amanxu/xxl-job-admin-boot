package com.xxl.job.admin.core.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-29 11:10
 */
@Getter
public enum ErrorCodeEnum implements Serializable {

    AUTH_ERR(900100, "鉴权失败"),
    JWT_EXPIRED_ERR(900101, "登陆超时"),
    BIZ_ERR(999999, "业务异常"),
    USER_ADD_ERR(100100, "用户创建失败"),
    USER_UPDATE_ERR(100101, "用户更新失败"),
    USER_REMOVE_ERR(100102, "用户删除失败"),
    USER_NOT_EXIST_ERR(100103, "用户不存在"),
    USER_EXIST_ERR(100103, "用户已存在"),
    AUTH_ADD_USER_NULL_ERR(200100, "用户不能为空"),
    AUTH_ADD_GROUP_NULL_ERR(200101, "执行器集合不能为空"),
    PWD_ERR(100104, "用户名或密码错误"),
    GROUP_EXISTS_ERR(300100, "APPNAME已存在"),
    GROUP_EXISTS_JOB_ERR(300101, "当前执行器下已存在该作业");

    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(Integer code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.code.equals(code)) {
                return errorCodeEnum.getMsg();
            }
        }
        return null;
    }
}
