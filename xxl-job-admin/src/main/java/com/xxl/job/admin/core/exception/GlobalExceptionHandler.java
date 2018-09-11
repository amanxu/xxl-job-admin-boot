package com.xxl.job.admin.core.exception;

import com.xxl.job.admin.core.enums.ErrorCodeEnum;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-19 16:00
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ReturnT defaultExceptionHandler(Exception ex) {
        log.error("GlobalExceptionHandler DefaultExceptionHandler:{}", ex);
        return new ReturnT(ErrorCodeEnum.BIZ_ERR.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ReturnT businessExceptionHandler(BusinessException ex) {
        log.error("GlobalExceptionHandler BusinessExceptionHandler:{}", ex);
        return new ReturnT(ex.getCode(), ex.getMessage());
    }
}
