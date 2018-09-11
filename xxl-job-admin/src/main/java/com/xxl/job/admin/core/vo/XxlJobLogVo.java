package com.xxl.job.admin.core.vo;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 21:05
 */
@Setter
@Getter
public class XxlJobLogVo extends XxlJobLog {

    private String jobName;
}
