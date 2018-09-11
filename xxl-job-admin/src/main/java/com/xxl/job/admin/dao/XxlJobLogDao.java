package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 *
 * @author xuxueli 2016-1-12 18:03:06
 */
public interface XxlJobLogDao {

    public List<XxlJobLog> pageList(@Param("offset") int offset,
                                    @Param("pagesize") int pagesize,
                                    @Param("jobGroup") int jobGroup,
                                    @Param("jobId") int jobId,
                                    @Param("triggerTimeStart") Date triggerTimeStart,
                                    @Param("triggerTimeEnd") Date triggerTimeEnd,
                                    @Param("logStatus") int logStatus);

    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("jobGroup") int jobGroup,
                             @Param("jobId") int jobId,
                             @Param("triggerTimeStart") Date triggerTimeStart,
                             @Param("triggerTimeEnd") Date triggerTimeEnd,
                             @Param("logStatus") int logStatus);

    public List<XxlJobLog> pageListByUserId(@Param("userId") Integer userId,
                                            @Param("offset") int offset,
                                            @Param("pagesize") int pagesize,
                                            @Param("jobGroup") Integer jobGroup,
                                            @Param("jobId") Integer jobId,
                                            @Param("triggerTimeStart") Date triggerTimeStart,
                                            @Param("triggerTimeEnd") Date triggerTimeEnd,
                                            @Param("logStatus") Integer logStatus);

    public int pageListCountByUserId(@Param("userId") Integer userId,
                                     @Param("offset") int offset,
                                     @Param("pagesize") int pagesize,
                                     @Param("jobGroup") Integer jobGroup,
                                     @Param("jobId") Integer jobId,
                                     @Param("triggerTimeStart") Date triggerTimeStart,
                                     @Param("triggerTimeEnd") Date triggerTimeEnd,
                                     @Param("logStatus") Integer logStatus);

    public XxlJobLog load(@Param("id") int id);

    public int save(XxlJobLog xxlJobLog);

    public int updateTriggerInfo(XxlJobLog xxlJobLog);

    public int updateHandleInfo(XxlJobLog xxlJobLog);

    public int delete(@Param("jobId") int jobId);

    public int triggerCountByHandleCode(@Param("handleCode") int handleCode);

    public int triggerCountByHandleCodeAndUserId(@Param("handleCode") int handleCode,
                                                 @Param("userId") Integer userId);

    public List<Map<String, Object>> triggerCountByDay(@Param("from") Date from,
                                                       @Param("to") Date to);

    public List<Map<String, Object>> triggerCountByDayAndUser(@Param("userId") Integer userId,
                                                              @Param("from") Date from,
                                                              @Param("to") Date to);

    public int clearLog(@Param("jobGroup") int jobGroup,
                        @Param("jobId") int jobId,
                        @Param("clearBeforeTime") Date clearBeforeTime,
                        @Param("clearBeforeNum") int clearBeforeNum);

}
