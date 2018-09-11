package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;


/**
 * job info
 *
 * @author xuxueli 2016-1-12 18:03:45
 */
public interface XxlJobInfoDao {

    public List<XxlJobInfo> pageList(@Param("userId") Integer userId,
                                     @Param("offset") int offset,
                                     @Param("pagesize") int pagesize,
                                     @Param("jobGroup") int jobGroup,
                                     @Param("jobDesc") String jobDesc,
                                     @Param("executorHandler") String executorHandler);

    public int pageListCount(@Param("userId") Integer userId,
                             @Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("jobGroup") int jobGroup,
                             @Param("jobDesc") String jobDesc,
                             @Param("executorHandler") String executorHandler);

    public int save(XxlJobInfo info);

    public XxlJobInfo loadById(@Param("id") int id);

    public int update(XxlJobInfo item);

    public int delete(@Param("id") int id);

    public List<XxlJobInfo> getJobsByGroup(@Param("jobGroup") int jobGroup);

    public int findAllCount();

    public int findJobCountByUser(@Param("userId") Integer userId);

    public List<XxlJobInfo> batchJobsByIds(@Param("ids") Set<Integer> ids);

    public List<XxlJobInfo> findJobsByGroup(@Param("groupId") Integer groupId);
}
