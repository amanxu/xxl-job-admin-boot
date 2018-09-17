package com.xxl.job.admin.service;

import com.github.pagehelper.PageInfo;
import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.model.XxlJobUserApiLog;
import com.xxl.job.admin.core.vo.XxlJobUserVo;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 8:14
 */
public interface XxlJobUserApiLogService {

    XxlJobUserApiLog save(XxlJobUserApiLog xxlJobUserApiLog);

}
