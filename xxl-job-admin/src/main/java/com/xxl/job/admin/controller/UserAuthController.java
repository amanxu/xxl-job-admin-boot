package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.dto.UserAuthSaveDto;
import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.vo.XxlJobUserAuthVo;
import com.xxl.job.admin.service.XxlJobUserAuthService;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 13:13
 */
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private XxlJobUserAuthService jobUserAuthService;

    @RequestMapping("/list")
    public ReturnT<XxlJobUserAuthVo> userAuth(@Validated @RequestBody UserPageDto userPageDto) {
        return new ReturnT(jobUserAuthService.findAuthPage(userPageDto));
    }

    /**
     * 权限保存
     *
     * @return
     */
    @RequestMapping("/authOperate")
    @ResponseBody
    public ReturnT authAddAndUpdate(@RequestBody UserAuthSaveDto userAuthSaveDto) {
        jobUserAuthService.saveUserAuth(userAuthSaveDto);
        return ReturnT.SUCCESS;
    }
}
