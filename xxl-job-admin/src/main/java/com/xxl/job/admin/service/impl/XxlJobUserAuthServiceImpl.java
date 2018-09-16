package com.xxl.job.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxl.job.admin.core.dto.UserAuthSaveDto;
import com.xxl.job.admin.core.dto.UserExecutorLink;
import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.enums.ErrorCodeEnum;
import com.xxl.job.admin.core.enums.UserTypeEnum;
import com.xxl.job.admin.core.exception.BusinessException;
import com.xxl.job.admin.core.model.XxlJobUserExecutor;
import com.xxl.job.admin.core.vo.XxlJobExecutorVo;
import com.xxl.job.admin.core.vo.XxlJobUserAuthVo;
import com.xxl.job.admin.dao.XxlJobUserDao;
import com.xxl.job.admin.dao.XxlJobUserExecutorDao;
import com.xxl.job.admin.service.XxlJobUserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 13:23
 */
@Slf4j
@Service
public class XxlJobUserAuthServiceImpl implements XxlJobUserAuthService {

    @Resource
    private XxlJobUserDao jobUserDao;

    @Resource
    private XxlJobUserExecutorDao jobUserExecutorDao;

    @Override
    public PageInfo<XxlJobUserAuthVo> findAuthPage(UserPageDto userPageDto) {
        PageHelper.startPage(userPageDto.getPageNo(), userPageDto.getPageSize());
        // 1.分页查询用户
        List<XxlJobUserAuthVo> jobUserVos = jobUserDao.findAuthPage(userPageDto.getUserName());
        if (CollectionUtils.isEmpty(jobUserVos)) {
            return new PageInfo<>(jobUserVos);
        }
        PageInfo<XxlJobUserAuthVo> pageInfo = new PageInfo<>(jobUserVos);
        List<Integer> userIds = jobUserVos.stream().map(XxlJobUserAuthVo::getId).collect(Collectors.toList());
        // 2.分页查询用户和执行器的关联数据
        List<XxlJobUserExecutor> userExecutorLinks = jobUserExecutorDao.batchQueryLink(userIds);
        Map<Integer, List<XxlJobUserExecutor>> listLinkMap = userExecutorLinks.stream().collect(Collectors.groupingBy(XxlJobUserExecutor::getUserId));
        pageInfo.setList(pageInfo.getList().stream().map(vo -> {
            List<XxlJobUserExecutor> userLinks = listLinkMap.get(vo.getId());
            if (CollectionUtils.isEmpty(userLinks)) {
                vo.setSelectedGroupIds(Collections.emptyList());
            } else {
                vo.setSelectedGroupIds(userLinks.stream().map(XxlJobUserExecutor::getExecutorId).collect(Collectors.toList()));
            }
            vo.setUserTypeDesc(UserTypeEnum.getMsgByCode(vo.getUserType()));
            return vo;
        }).collect(Collectors.toList()));
        return pageInfo;
    }

    private XxlJobExecutorVo convertLinkToVo(UserExecutorLink link) {
        XxlJobExecutorVo executorVo = new XxlJobExecutorVo();
        executorVo.setId(link.getId());
        executorVo.setAppName(link.getAppName());
        executorVo.setTitle(link.getTitle());
        return executorVo;
    }

    @Override
    public void saveUserAuth(UserAuthSaveDto userAuthSaveDto) {
        if (userAuthSaveDto.getUserId() == null) {
            throw new BusinessException(ErrorCodeEnum.AUTH_ADD_USER_NULL_ERR.getCode(), ErrorCodeEnum.AUTH_ADD_USER_NULL_ERR.getMsg());
        }
        // 1. 批量删除原有的用户权限
        jobUserExecutorDao.delByUserId(userAuthSaveDto.getUserId());
        if (!CollectionUtils.isEmpty(userAuthSaveDto.getGroupIds())) {
            List<XxlJobUserExecutor> userExecutors = userAuthSaveDto.getGroupIds().stream().map(g -> {
                XxlJobUserExecutor userExecutor = new XxlJobUserExecutor();
                userExecutor.setUserId(userAuthSaveDto.getUserId());
                userExecutor.setExecutorId(g);
                return userExecutor;
            }).collect(Collectors.toList());
            // 2. 批量插入当前的用户权限
            jobUserExecutorDao.batchInsertLink(userExecutors);
        }
    }
}
