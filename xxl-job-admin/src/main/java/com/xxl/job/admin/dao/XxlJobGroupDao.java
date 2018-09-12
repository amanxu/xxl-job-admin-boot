package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.dto.UserExecutorLink;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.vo.XxlJobGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
public interface XxlJobGroupDao {

    List<XxlJobGroup> findAll();

    List<XxlJobGroupVo> findByFilter(@Param("userId") Integer userId,
                                     @Param("appName") String appName,
                                     @Param("title") String title);

    List<XxlJobGroup> findByUserId(@Param("userId") Integer userId);

    List<XxlJobGroup> findByAddressType(@Param("addressType") int addressType);

    int save(XxlJobGroup xxlJobGroup);

    int update(XxlJobGroup xxlJobGroup);

    int remove(@Param("id") int id);

    XxlJobGroup load(@Param("id") int id);

    List<UserExecutorLink> batchGroupByUser(@Param("ids") List<Integer> ids);

    XxlJobGroup findGroupByAppName(@Param("appName") String appName);
}
