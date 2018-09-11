package com.xxl.job.admin.service;

import com.github.pagehelper.PageInfo;
import com.xxl.job.admin.core.dto.UserAuthSaveDto;
import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.vo.XxlJobUserAuthVo;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 13:22
 */
public interface XxlJobUserAuthService {

    PageInfo<XxlJobUserAuthVo> findAuthPage(UserPageDto userPageDto);

    void saveUserAuth(UserAuthSaveDto userAuthSaveDto);
}
