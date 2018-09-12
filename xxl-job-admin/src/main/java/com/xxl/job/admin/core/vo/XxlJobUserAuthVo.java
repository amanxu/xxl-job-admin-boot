package com.xxl.job.admin.core.vo;

import com.xxl.job.admin.core.model.XxlJobGroup;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-02 13:15
 */
@Setter
@Getter
public class XxlJobUserAuthVo implements Serializable {

    private Integer id;
    private String userName;
    private String realName;
    private Integer userType;
    private String userTypeDesc;
    private List<XxlJobGroup> jobGroups;
    private List<Integer> selectedGroupIds;

}
