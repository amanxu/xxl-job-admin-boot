package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.vo.XxlJobUserAuthVo;
import com.xxl.job.admin.core.vo.XxlJobUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoxue.nie
 * @description 用户表
 * @date 2018-08-28
 */
public interface XxlJobUserDao {

    public List<XxlJobUser> findAll();

    public List<XxlJobUserVo> findPage(@Param("userName") String userName);

    public List<XxlJobUserAuthVo> findAuthPage(@Param("userName") String userName);

    public XxlJobUser findByUserName(@Param("userName") String userName);

    public int save(XxlJobUser xxlJobUser);

    public int update(XxlJobUser xxlJobUser);

    public int batchDelete(@Param("id") Integer id);

    public XxlJobUser findById(@Param("id") Integer id);
}
