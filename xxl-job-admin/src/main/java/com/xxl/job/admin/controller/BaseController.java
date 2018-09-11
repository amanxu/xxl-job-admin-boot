package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.enums.ErrorCodeEnum;
import com.xxl.job.admin.core.enums.UserTypeEnum;
import com.xxl.job.admin.core.exception.BusinessException;
import com.xxl.job.admin.core.model.XxlJobUser;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-28 13:31
 */
public class BaseController {

    /**
     * 获取当前线程变量副本中的用户
     *
     * @return
     */
    public XxlJobUser getReqUser(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("CLAIMS");
        XxlJobUser xxlJobUser = new XxlJobUser();
        xxlJobUser.setUserName((String) claims.get("username"));
        xxlJobUser.setId((Integer) claims.get("userId"));
        xxlJobUser.setUserType((Integer) claims.get("userType"));
        return xxlJobUser;
    }

    /**
     * 获取当前线程变量副本中的用户的ID
     *
     * @return
     */
    public Integer getReqUserId(HttpServletRequest request) {
        if (getReqUser(request) == null) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXIST_ERR.getCode(), ErrorCodeEnum.USER_NOT_EXIST_ERR.getMsg());
        }
        return getReqUser(request).getId();
    }

    /**
     * 返回用户ID，如果是超级管理员返回null
     *
     * @return
     */
    public Integer getUIdExAdmin(HttpServletRequest request) {
        if (isSuperAdmin(request)) {
            return null;
        }
        return getReqUserId(request);
    }

    /**
     * 判断当前用户是否是超级管理员
     *
     * @return
     */
    public Boolean isSuperAdmin(HttpServletRequest request) {
        if (getReqUser(request) == null) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXIST_ERR.getCode(), ErrorCodeEnum.USER_NOT_EXIST_ERR.getMsg());
        }
        if (UserTypeEnum.ADMIN.getCode().equals(getReqUser(request).getUserType())) {
            return true;
        }
        return false;
    }
}
