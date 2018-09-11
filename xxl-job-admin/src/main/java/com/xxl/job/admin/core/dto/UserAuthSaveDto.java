package com.xxl.job.admin.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-04 17:30
 */
@Setter
@Getter
public class UserAuthSaveDto implements Serializable {

    private Integer userId;
    private List<Integer> groupIds;

}
