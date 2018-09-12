package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobUserExecutor;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.dao.XxlJobUserExecutorDao;
import com.xxl.job.admin.service.XxlJobGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-28 13:40
 */
@Service
@Slf4j
public class XxlJobGroupServiceImpl implements XxlJobGroupService {

    @Resource
    public XxlJobGroupDao xxlJobGroupDao;

    @Resource
    private XxlJobUserExecutorDao jobUserExecutorDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createGroup(Integer userId, XxlJobGroup xxlJobGroup) {
        int ret = xxlJobGroupDao.save(xxlJobGroup);
        // 增加用户与执行器的关联关系
        XxlJobUserExecutor jobUserExecutor = new XxlJobUserExecutor();
        jobUserExecutor.setUserId(userId);
        jobUserExecutor.setExecutorId(xxlJobGroup.getId());
        int retLink = jobUserExecutorDao.save(jobUserExecutor);
        return (ret > 0 && retLink > 0) ? 1 : 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeGroup(Integer id) {
        int ret = xxlJobGroupDao.remove(id);
        int retLink = jobUserExecutorDao.delByUserIdAndExeId(id);
        return (ret > 0 && retLink > 0) ? 1 : 0;
    }

    @Override
    public XxlJobGroup fingGroupByAppName(String appName) {
        return xxlJobGroupDao.findGroupByAppName(appName);
    }
}
