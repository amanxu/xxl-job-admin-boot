package com.xxl.job.admin.core.enums;

import lombok.Getter;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-28 12:59
 */
@Getter
public enum AddressTypeEnum {

    ADMIN(0, "自动注册"),
    NORMAL_USER(1, "手动录入");

    private Integer code;
    private String msg;

    AddressTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(Integer code) {
        for (AddressTypeEnum typeEnum : AddressTypeEnum.values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum.getMsg();
            }
        }
        return null;
    }
}
