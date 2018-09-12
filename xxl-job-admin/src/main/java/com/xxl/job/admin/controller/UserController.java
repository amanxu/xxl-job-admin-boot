package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.dto.UserPageDto;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.vo.XxlJobUserVo;
import com.xxl.job.admin.service.XxlJobUserService;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 8:11
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private XxlJobUserService jobUserService;

    @RequestMapping("/create")
    public ReturnT userAdd(@RequestBody XxlJobUser jobUser) {
        jobUserService.createUser(jobUser);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/update")
    public ReturnT userUpdate(@RequestBody XxlJobUser jobUser) {
        jobUserService.updateUser(jobUser);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/detail")
    public ReturnT userDetail(@RequestParam("id") Integer id) {
        return new ReturnT(jobUserService.findById(id));
    }

    @RequestMapping("/tokenUserInfo")
    public ReturnT userDetailByToken(HttpServletRequest request) {
        Integer userId = getReqUserId(request);
        XxlJobUser xxlJobUser = jobUserService.findById(userId);
        XxlJobUserVo jobUserVo = new XxlJobUserVo();
        BeanUtils.copyProperties(xxlJobUser, jobUserVo);
        if (xxlJobUser.getUserType() == 0) {
            jobUserVo.setRoles(Lists.newArrayList("admin"));
        } else {
            jobUserVo.setRoles(Lists.newArrayList("normal"));
        }
        return new ReturnT(jobUserVo);
    }

    @RequestMapping("/detailByName")
    public ReturnT userDetail(@RequestParam("userName") String userName) {
        return new ReturnT(jobUserService.findByUserName(userName));
    }

    @RequestMapping("/list")
    public ReturnT list(@Validated @RequestBody UserPageDto userPageDto) {
        return new ReturnT(jobUserService.findUserPage(userPageDto));
    }

    @RequestMapping("/batchDel")
    public ReturnT batchDel(Integer id) {
        jobUserService.batchDelete(id);
        return ReturnT.SUCCESS;
    }
}
