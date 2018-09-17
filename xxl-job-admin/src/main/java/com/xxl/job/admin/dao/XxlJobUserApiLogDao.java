package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.model.XxlJobUserApiLog;
import com.xxl.job.admin.core.vo.XxlJobUserAuthVo;
import com.xxl.job.admin.core.vo.XxlJobUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxue.nie
 * @description 用户表日志记录表
 * @date 2018-08-28
 */
public interface XxlJobUserApiLogDao {

    int save(XxlJobUserApiLog xxlJobUserApiLog);

}
