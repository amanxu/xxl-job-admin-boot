package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.enums.ErrorCodeEnum;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.core.schedule.XxlJobDynamicScheduler;
import com.xxl.job.admin.core.util.DateUtil;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.core.vo.XxlJobLogVo;
import com.xxl.job.admin.dao.XxlJobInfoDao;
import com.xxl.job.admin.dao.XxlJobLogDao;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.model.LogResult;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/joblog")
public class JobLogController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(JobLogController.class);

    @Resource
    public XxlJobInfoDao xxlJobInfoDao;
    @Resource
    public XxlJobLogDao xxlJobLogDao;

    @RequestMapping("/getJobsByGroup")
    @ResponseBody
    public ReturnT<List<XxlJobInfo>> getJobsByGroup(int jobGroup) {
        List<XxlJobInfo> list = xxlJobInfoDao.getJobsByGroup(jobGroup);
        return new ReturnT<List<XxlJobInfo>>(list);
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public ReturnT<Map<String, Object>> pageList(HttpServletRequest request, @RequestParam(required = false, defaultValue = "0") int start,
                                                 @RequestParam(required = false, defaultValue = "10") int length,
                                                 Integer jobGroup, Integer jobId, Integer logStatus,
                                                 @RequestParam(required = false, value = "filterTime[]") String[] filterTime) {

        Date triggerTimeStart = null;
        Date triggerTimeEnd = null;
        if (ArrayUtils.isNotEmpty(filterTime) && filterTime.length == 2) {
            triggerTimeStart = DateUtil.dateUtc(filterTime[0]);
            triggerTimeEnd = DateUtil.dateUtc(filterTime[1]);
        }
        Integer userId = getUIdExAdmin(request);
        // 超级管理员拥有所有日志的权限
        List<XxlJobLog> list = xxlJobLogDao.pageListByUserId(userId, start, length, jobGroup, jobId, triggerTimeStart, triggerTimeEnd, logStatus);
        int list_count = xxlJobLogDao.pageListCountByUserId(userId, start, length, jobGroup, jobId, triggerTimeStart, triggerTimeEnd, logStatus);
        Set<Integer> ids = list.stream().map(XxlJobLog::getJobId).collect(Collectors.toSet());
        Map<Integer, List<XxlJobInfo>> listMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(ids)) {
            List<XxlJobInfo> jobInfos = xxlJobInfoDao.batchJobsByIds(ids);
            listMap = jobInfos.stream().collect(Collectors.groupingBy(XxlJobInfo::getId));
        }
        Map<Integer, List<XxlJobInfo>> finalListMap = listMap;
        List<XxlJobLogVo> listVo = list.stream().map(l -> {
            XxlJobLogVo jobLogVo = new XxlJobLogVo();
            BeanUtils.copyProperties(l, jobLogVo);
            List<XxlJobInfo> jobInfoList = finalListMap.get(l.getJobId());
            if (!CollectionUtils.isEmpty(jobInfoList) && jobInfoList.get(0) != null) {
                jobLogVo.setJobName(jobInfoList.get(0).getJobDesc());
            }
            return jobLogVo;
        }).collect(Collectors.toList());
        // package result
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("recordsTotal", list_count);        // 总记录数
        maps.put("recordsFiltered", list_count);    // 过滤后的总记录数
        maps.put("data", listVo);                    // 分页列表
        return new ReturnT<>(maps);
    }

    @RequestMapping("/logDetailPage")
    @ResponseBody
    public ReturnT logDetailPage(@Param("id") int id) {

        // base check
        XxlJobLog jobLog = xxlJobLogDao.load(id);
        return new ReturnT(jobLog);
    }

    @RequestMapping("/logDetailCat")
    @ResponseBody
    public ReturnT<LogResult> logDetailCat(String executorAddress, long triggerTime, int logId, int fromLineNum) {
        try {
            ExecutorBiz executorBiz = XxlJobDynamicScheduler.getExecutorBiz(executorAddress);
            ReturnT<LogResult> logResult = executorBiz.log(triggerTime, logId, fromLineNum);

            // is end
            if (logResult.getContent() != null && logResult.getContent().getFromLineNum() > logResult.getContent().getToLineNum()) {
                XxlJobLog jobLog = xxlJobLogDao.load(logId);
                if (jobLog.getHandleCode() > 0) {
                    logResult.getContent().setEnd(true);
                }
            }

            return logResult;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<LogResult>(ReturnT.FAIL_CODE, e.getMessage());
        }
    }

    @RequestMapping("/logKill")
    @ResponseBody
    public ReturnT<String> logKill(@Param("id") int id) {
        // base check
        XxlJobLog log = xxlJobLogDao.load(id);
        XxlJobInfo jobInfo = xxlJobInfoDao.loadById(log.getJobId());
        if (jobInfo == null) {
            return new ReturnT<String>(500, I18nUtil.getString("jobinfo_glue_jobid_unvalid"));
        }
        if (ReturnT.SUCCESS_CODE != log.getTriggerCode()) {
            return new ReturnT<String>(500, I18nUtil.getString("joblog_kill_log_limit"));
        }

        // request of kill
        ReturnT<String> runResult = null;
        try {
            ExecutorBiz executorBiz = XxlJobDynamicScheduler.getExecutorBiz(log.getExecutorAddress());
            runResult = executorBiz.kill(jobInfo.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            runResult = new ReturnT(ErrorCodeEnum.GROUP_STOP_FAIL_ERR.getCode(), ErrorCodeEnum.GROUP_STOP_FAIL_ERR.getMsg());
        }

        if (ReturnT.SUCCESS_CODE == runResult.getCode()) {
            log.setHandleCode(ReturnT.FAIL_CODE);
            log.setHandleMsg(I18nUtil.getString("joblog_kill_log_byman") + ":" + (runResult.getMsg() != null ? runResult.getMsg() : ""));
            log.setHandleTime(new Date());
            xxlJobLogDao.updateHandleInfo(log);
            return new ReturnT<String>(runResult.getMsg());
        } else {
            return new ReturnT<String>(500, runResult.getMsg());
        }
    }

    @RequestMapping("/clearLog")
    @ResponseBody
    public ReturnT<String> clearLog(int jobGroup, int jobId, int type) {

        Date clearBeforeTime = null;
        int clearBeforeNum = 0;
        if (type == 1) {
            clearBeforeTime = DateUtils.addMonths(new Date(), -1);    // 清理一个月之前日志数据
        } else if (type == 2) {
            clearBeforeTime = DateUtils.addMonths(new Date(), -3);    // 清理三个月之前日志数据
        } else if (type == 3) {
            clearBeforeTime = DateUtils.addMonths(new Date(), -6);    // 清理六个月之前日志数据
        } else if (type == 4) {
            clearBeforeTime = DateUtils.addYears(new Date(), -1);    // 清理一年之前日志数据
        } else if (type == 5) {
            clearBeforeNum = 1000;        // 清理一千条以前日志数据
        } else if (type == 6) {
            clearBeforeNum = 10000;        // 清理一万条以前日志数据
        } else if (type == 7) {
            clearBeforeNum = 30000;        // 清理三万条以前日志数据
        } else if (type == 8) {
            clearBeforeNum = 100000;    // 清理十万条以前日志数据
        } else if (type == 9) {
            clearBeforeNum = 0;            // 清理所有日志数据
        } else {
            return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("joblog_clean_type_unvalid"));
        }

        xxlJobLogDao.clearLog(jobGroup, jobId, clearBeforeTime, clearBeforeNum);
        return ReturnT.SUCCESS;
    }

}
