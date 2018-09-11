package com.xxl.job.admin.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-29 11:14
 */
@Setter
@Getter
public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
