package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.util.EnumAssembleUtil;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController extends BaseController {

    @Resource
    private XxlJobGroupDao xxlJobGroupDao;
    @Resource
    private XxlJobService xxlJobService;

    @RequestMapping("/pageList")
    @ResponseBody
    public ReturnT<Map<String, Object>> pageList(HttpServletRequest request, @RequestParam(required = false, defaultValue = "0") int start,
                                                 @RequestParam(required = false, defaultValue = "10") int length,
                                                 Integer jobGroup, String jobDesc, String executorHandler, String filterTime) {
        jobGroup = jobGroup == null ? 0 : jobGroup;
        Map<String, Object> mapPage = xxlJobService.pageList(getUIdExAdmin(request), start, length, jobGroup, jobDesc, executorHandler, filterTime);
        // 任务组
        List<XxlJobGroup> jobGroupList = xxlJobGroupDao.findByUserId(getUIdExAdmin(request));
        mapPage.put("groups", jobGroupList);
        // 路由策略-列表
        mapPage.put("routeStrategies", EnumAssembleUtil.assembleRouteStrategies());
        // Glue类型-字典
        mapPage.put("glueTypes", EnumAssembleUtil.assembleGlueTypes());
        // 阻塞处理策略-字典
        mapPage.put("blockStrategies", EnumAssembleUtil.assembleBlockStrategies());
        // 失败处理策略-字典
        mapPage.put("failStrategies", EnumAssembleUtil.assembleFailStrategies());
        return new ReturnT<>(mapPage);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnT<String> add(@RequestBody XxlJobInfo jobInfo) {

        return xxlJobService.add(jobInfo);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(@RequestBody XxlJobInfo jobInfo) {
        return xxlJobService.update(jobInfo);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnT<String> remove(int id) {
        return xxlJobService.remove(id);
    }

    @RequestMapping("/pause")
    @ResponseBody
    public ReturnT<String> pause(int id) {
        return xxlJobService.pause(id);
    }

    @RequestMapping("/resume")
    @ResponseBody
    public ReturnT<String> resume(int id) {
        return xxlJobService.resume(id);
    }

    @RequestMapping("/trigger")
    @ResponseBody
    public ReturnT<String> triggerJob(int id) {
        return xxlJobService.triggerJob(id);
    }

    @RequestMapping("/jobsByGroup")
    @ResponseBody
    public ReturnT<List<XxlJobInfo>> queryJobByGroup(Integer groupId) {
        return new ReturnT(xxlJobService.findJobsByGroup(groupId));
    }

}
