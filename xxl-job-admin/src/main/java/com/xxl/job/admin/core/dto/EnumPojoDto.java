package com.xxl.job.admin.core.dto;

import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-03 19:42
 */
@Setter
@Getter
public class EnumPojoDto implements Serializable {

    private String code;
    private String msg;
}
