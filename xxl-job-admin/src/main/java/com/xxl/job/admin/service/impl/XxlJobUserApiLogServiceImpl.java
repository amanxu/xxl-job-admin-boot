package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobUserApiLog;
import com.xxl.job.admin.dao.XxlJobUserApiLogDao;
import com.xxl.job.admin.service.XxlJobUserApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-16 22:07
 */
@Slf4j
@Service(value = "xxlJobUserApiLogService")
public class XxlJobUserApiLogServiceImpl implements XxlJobUserApiLogService {

    @Resource
    private XxlJobUserApiLogDao xxlJobUserApiLogDao;

    @Override
    public XxlJobUserApiLog save(XxlJobUserApiLog xxlJobUserApiLog) {
        xxlJobUserApiLogDao.save(xxlJobUserApiLog);
        return xxlJobUserApiLog;
    }
}
