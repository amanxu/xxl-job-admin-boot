package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobGroup;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-08-28 13:39
 */
public interface XxlJobGroupService {

    int createGroup(Integer userId, XxlJobGroup xxlJobGroup);

    int removeGroup(Integer id);

    XxlJobGroup fingGroupByAppName(String appName);
}
