package com.xxl.job.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxl.job.admin.core.dto.GroupPageDto;
import com.xxl.job.admin.core.enums.AddressTypeEnum;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.core.vo.XxlJobGroupVo;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.dao.XxlJobInfoDao;
import com.xxl.job.admin.service.XxlJobGroupService;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * job group controller
 *
 * @author xuxueli 2016-10-02 20:52:56
 */
@Controller
@RequestMapping("/jobgroup")
public class JobGroupController extends BaseController {

    @Resource
    public XxlJobInfoDao xxlJobInfoDao;
    @Resource
    public XxlJobGroupDao xxlJobGroupDao;

    @Autowired
    private XxlJobGroupService jobGroupService;

    @RequestMapping("/list")
    @ResponseBody
    public ReturnT groupPage(HttpServletRequest request, @Validated @RequestBody GroupPageDto groupPageDto) {

        PageHelper.startPage(groupPageDto.getPageNo(), groupPageDto.getPageSize());
        List<XxlJobGroupVo> groups = xxlJobGroupDao.findByFilter(getUIdExAdmin(request), groupPageDto.getAppName(), groupPageDto.getTitle());
        PageInfo<XxlJobGroupVo> pageInfo = new PageInfo<>(groups);
        pageInfo.setList(pageInfo.getList().stream().map(g -> {
            g.setAddressTypeDesc(AddressTypeEnum.getMsgByCode(g.getAddressType()));
            return g;
        }).collect(Collectors.toList()));
        return new ReturnT(pageInfo);
    }

    @RequestMapping("/listAll")
    @ResponseBody
    public ReturnT groupListAll(HttpServletRequest request) {
        List<XxlJobGroup> list = xxlJobGroupDao.findByUserId(getUIdExAdmin(request));
        return new ReturnT(list);
    }

    @RequestMapping("/save")
    @ResponseBody
    public ReturnT<String> save(HttpServletRequest request, @RequestBody XxlJobGroup xxlJobGroup) {

        // 参数校验
        if (xxlJobGroup.getAppName() == null || StringUtils.isBlank(xxlJobGroup.getAppName())) {
            return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + "AppName"));
        }
        if (xxlJobGroup.getAppName().length() < 4 || xxlJobGroup.getAppName().length() > 64) {
            return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_appName_length"));
        }
        if (xxlJobGroup.getTitle() == null || StringUtils.isBlank(xxlJobGroup.getTitle())) {
            return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title")));
        }
        if (xxlJobGroup.getAddressType() != 0) {
            if (StringUtils.isBlank(xxlJobGroup.getAddressList())) {
                return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_addressType_limit"));
            }
            String[] addresss = xxlJobGroup.getAddressList().split(",");
            for (String item : addresss) {
                if (StringUtils.isBlank(item)) {
                    return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_registryList_unvalid"));
                }
            }
        }
        int ret = jobGroupService.createGroup(getReqUserId(request), xxlJobGroup);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(@RequestBody XxlJobGroup xxlJobGroup) {
        // valid
        if (xxlJobGroup.getAppName() == null || StringUtils.isBlank(xxlJobGroup.getAppName())) {
            return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + "AppName"));
        }
        if (xxlJobGroup.getAppName().length() < 4 || xxlJobGroup.getAppName().length() > 64) {
            return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_appName_length"));
        }
        if (xxlJobGroup.getTitle() == null || StringUtils.isBlank(xxlJobGroup.getTitle())) {
            return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title")));
        }
        if (xxlJobGroup.getAddressType() != 0) {
            if (StringUtils.isBlank(xxlJobGroup.getAddressList())) {
                return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_addressType_limit"));
            }
            String[] addresss = xxlJobGroup.getAddressList().split(",");
            for (String item : addresss) {
                if (StringUtils.isBlank(item)) {
                    return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_registryList_unvalid"));
                }
            }
        }

        int ret = xxlJobGroupDao.update(xxlJobGroup);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnT<String> remove(int id) {

        // valid
        int count = xxlJobInfoDao.pageListCount(null, 0, 10, id, null, null);
        if (count > 0) {
            return new ReturnT<String>(500, I18nUtil.getString("jobgroup_del_limit_0"));
        }

        List<XxlJobGroup> allList = xxlJobGroupDao.findAll();
        if (allList.size() == 1) {
            return new ReturnT<String>(500, I18nUtil.getString("jobgroup_del_limit_1"));
        }

        int ret = jobGroupService.removeGroup(id);

        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

}
