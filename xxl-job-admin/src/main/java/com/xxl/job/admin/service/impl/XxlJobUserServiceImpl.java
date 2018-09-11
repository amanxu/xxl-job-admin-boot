package com.xxl.job.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.enums.ErrorCodeEnum;
import com.xxl.job.admin.core.enums.UserTypeEnum;
import com.xxl.job.admin.core.exception.BusinessException;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.model.XxlJobUserExecutor;
import com.xxl.job.admin.core.vo.XxlJobUserVo;
import com.xxl.job.admin.dao.XxlJobUserDao;
import com.xxl.job.admin.service.XxlJobUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 8:18
 */
@Slf4j
@Service
public class XxlJobUserServiceImpl implements XxlJobUserService {

    @Resource
    private XxlJobUserDao jobUserDao;

    @Override
    public int createUser(XxlJobUser jobUser) {

        XxlJobUser user = jobUserDao.findByUserName(jobUser.getUserName());
        if (user != null) {
            throw new BusinessException(ErrorCodeEnum.USER_EXIST_ERR.getCode(), ErrorCodeEnum.USER_EXIST_ERR.getMsg());
        }
        jobUser.setPassword("123456");
        jobUser.setCreateTime(new Date());
        int result = jobUserDao.save(jobUser);
        if (result <= 0) {
            throw new BusinessException(ErrorCodeEnum.USER_ADD_ERR.getCode(), ErrorCodeEnum.USER_ADD_ERR.getMsg());
        }
        return result;
    }

    @Override
    public int updateUser(XxlJobUser jobUser) {

        XxlJobUser user = jobUserDao.findByUserName(jobUser.getUserName());
        if (user != null && !user.getId().equals(jobUser.getId())) {
            throw new BusinessException(ErrorCodeEnum.USER_EXIST_ERR.getCode(), ErrorCodeEnum.USER_EXIST_ERR.getMsg());
        }
        jobUser.setUpdateTime(new Date());
        int result = jobUserDao.update(jobUser);
        if (result <= 0) {
            throw new BusinessException(ErrorCodeEnum.USER_UPDATE_ERR.getCode(), ErrorCodeEnum.USER_UPDATE_ERR.getMsg());
        }
        return result;
    }

    @Override
    public XxlJobUser findById(Integer userId) {
        return jobUserDao.findById(userId);
    }

    @Override
    public XxlJobUser findByUserName(String usreName) {
        return jobUserDao.findByUserName(usreName);
    }

    @Override
    public PageInfo<XxlJobUserVo> findUserPage(UserPageDto userPageDto) {
        PageHelper.startPage(userPageDto.getPageNo(), userPageDto.getPageSize());
        List<XxlJobUserVo> users = jobUserDao.findPage(userPageDto.getUserName());
        PageInfo<XxlJobUserVo> pageInfo = new PageInfo<>(users);
        pageInfo.setList(pageInfo.getList().stream().map(this::convertUserTypeDesc).collect(Collectors.toList()));
        return pageInfo;
    }

    private XxlJobUserVo convertUserTypeDesc(XxlJobUserVo jobUserVo) {
        jobUserVo.setUserTypeDesc(UserTypeEnum.getMsgByCode(jobUserVo.getUserType()));
        return jobUserVo;
    }

    @Override
    public int batchDelete(Integer id) {
        return jobUserDao.batchDelete(id);
    }
}
