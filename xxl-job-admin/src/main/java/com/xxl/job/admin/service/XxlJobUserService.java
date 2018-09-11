package com.xxl.job.admin.service;

import com.github.pagehelper.PageInfo;
import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.vo.XxlJobUserVo;
import com.xxl.job.admin.core.model.XxlJobUser;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 8:14
 */
public interface XxlJobUserService {

    int createUser(XxlJobUser jobUser);

    int updateUser(XxlJobUser jobUser);

    XxlJobUser findById(Integer userId);

    XxlJobUser findByUserName(String usreName);

    PageInfo<XxlJobUserVo> findUserPage(UserPageDto userPageDto);

    int batchDelete(Integer ids);

}
