package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobUserExecutor;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author xiaoxue.nie
 * @description 用户和执行器关联表
 * @date 2018-08-28
 */
public interface XxlJobUserExecutorDao {

    List<XxlJobUserExecutor> findAll();

    int save(XxlJobUserExecutor jobUserExecutor);

    int delByUserId(@Param("userId") Integer userId);

    int delByExecutorId(@Param("exeId") Integer exeId);

    /**
     * 根据用户id和执行器id删除关联关系
     *
     * @param exeId
     * @return
     */
    int delByUserIdAndExeId(@Param("exeId") Integer exeId);

    int batchInsertLink(@Param("userExecutors") List<XxlJobUserExecutor> userExecutors);

    List<XxlJobUserExecutor> batchQueryLink(@Param("userIds") List<Integer> userIds);

}
