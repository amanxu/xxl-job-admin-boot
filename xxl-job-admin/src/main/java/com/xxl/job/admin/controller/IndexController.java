package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermessionLimit;
import com.xxl.job.admin.core.conf.JwtConfigProperty;
import com.xxl.job.admin.core.dto.UserLoginDto;
import com.xxl.job.admin.core.enums.ErrorCodeEnum;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.util.DateUtil;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.core.util.JwtHelper;
import com.xxl.job.admin.dao.XxlJobUserDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping
public class IndexController extends BaseController {

    @Resource
    private XxlJobService xxlJobService;

    @Autowired
    private JwtConfigProperty jwtConfigProperty;

    @Autowired
    private XxlJobUserDao xxlJobUserDao;

    @PermessionLimit(limit = false)
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/chartInfo")
    @ResponseBody
    public ReturnT<Map<String, Object>> chartInfo(HttpServletRequest request,
                                                  @RequestParam(value = "startDate", required = false) String startDate,
                                                  @RequestParam(value = "endDate", required = false) String endDate) {
        Date startTime = null;
        Date endTime = null;
        if (StringUtils.isBlank(startDate)) {
            startTime = DateUtil.getAgoMonthDay();
        } else {
            startTime = DateUtil.dateUtc(startDate);
        }
        if (StringUtils.isBlank(endDate)) {
            endTime = new Date();
        } else {
            endTime = DateUtil.dateUtc(endDate);
        }
        Integer userId = getUIdExAdmin(request);
        // 块儿装分组
        Map<String, Object> dashboardMap = xxlJobService.dashboardInfo(userId);
        Map<String, Object> chartInfo = xxlJobService.chartInfo(userId, startTime, endTime);
        chartInfo.putAll(dashboardMap);
        return new ReturnT<>(chartInfo);
    }

    @PostMapping("/login")
    @ResponseBody
    public ReturnT loginDo(@RequestBody UserLoginDto userLoginDto) {

        if (StringUtils.isBlank(userLoginDto.getUsername()) || StringUtils.isBlank(userLoginDto.getPassword())) {
            return new ReturnT<String>(500, I18nUtil.getString("login_param_empty"));
        }
        XxlJobUser jobUser = xxlJobUserDao.findByUserName(userLoginDto.getUsername());
        if (jobUser == null) {
            return new ReturnT(ErrorCodeEnum.USER_NOT_EXIST_ERR.getCode(), ErrorCodeEnum.USER_NOT_EXIST_ERR.getMsg());
        }
        if (!userLoginDto.getPassword().equals(jobUser.getPassword())) {
            return new ReturnT(ErrorCodeEnum.PWD_ERR.getCode(), ErrorCodeEnum.PWD_ERR.getMsg());
        }
        String authToken = JwtHelper.createJwt(jobUser.getId(), jobUser.getUserName(), jobUser.getUserType(),
                jwtConfigProperty.getBase64Secret(), jwtConfigProperty.getExpiresTime());
        authToken = "bearer;" + authToken;
        return new ReturnT(authToken);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return ReturnT.SUCCESS;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
