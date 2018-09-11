package com.xxl.job.admin.core.util;

import com.xxl.job.admin.core.dto.EnumPojoDto;
import com.xxl.job.admin.core.enums.AddressTypeEnum;
import com.xxl.job.admin.core.enums.ExecutorFailStrategyEnum;
import com.xxl.job.admin.core.enums.UserTypeEnum;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-03 19:40
 */
@Slf4j
public class EnumAssembleUtil {

    /**
     * 路由策略-列表
     *
     * @return
     */
    public static List<EnumPojoDto> assembleRouteStrategies() {
        return Lists.newArrayList(ExecutorRouteStrategyEnum.values()).stream().map(e -> {
            EnumPojoDto enumPojoDto = new EnumPojoDto();
            enumPojoDto.setCode(e.name());
            enumPojoDto.setMsg(e.getTitle());
            return enumPojoDto;
        }).collect(Collectors.toList());
    }

    /**
     * Glue类型-字典
     *
     * @return
     */
    public static List<EnumPojoDto> assembleGlueTypes() {
        return Lists.newArrayList(GlueTypeEnum.values()).stream().map(e -> {
            EnumPojoDto enumPojoDto = new EnumPojoDto();
            enumPojoDto.setCode(e.name());
            enumPojoDto.setMsg(e.getDesc());
            return enumPojoDto;
        }).collect(Collectors.toList());
    }

    /**
     * 阻塞处理策略-字典
     *
     * @return
     */
    public static List<EnumPojoDto> assembleBlockStrategies() {
        return Lists.newArrayList(ExecutorBlockStrategyEnum.values()).stream().map(e -> {
            EnumPojoDto enumPojoDto = new EnumPojoDto();
            enumPojoDto.setCode(e.name());
            enumPojoDto.setMsg(e.getTitle());
            return enumPojoDto;
        }).collect(Collectors.toList());
    }

    /**
     * 失败处理策略-字典
     *
     * @return
     */
    public static List<EnumPojoDto> assembleFailStrategies() {
        return Lists.newArrayList(ExecutorFailStrategyEnum.values()).stream().map(e -> {
            EnumPojoDto enumPojoDto = new EnumPojoDto();
            enumPojoDto.setCode(e.name());
            enumPojoDto.setMsg(e.getTitle());
            return enumPojoDto;
        }).collect(Collectors.toList());
    }

    /**
     * 用户类型
     *
     * @return
     */
    public static List<EnumPojoDto> assembleUserType() {
        return Lists.newArrayList(UserTypeEnum.values()).stream().map(e -> {
            EnumPojoDto enumPojoDto = new EnumPojoDto();
            enumPojoDto.setCode(String.valueOf(e.getCode()));
            enumPojoDto.setMsg(e.getMsg());
            return enumPojoDto;
        }).collect(Collectors.toList());
    }

    /**
     * 注册类型
     *
     * @return
     */
    public static List<EnumPojoDto> assembleAddressType() {
        return Lists.newArrayList(AddressTypeEnum.values()).stream().map(e -> {
            EnumPojoDto enumPojoDto = new EnumPojoDto();
            enumPojoDto.setCode(String.valueOf(e.getCode()));
            enumPojoDto.setMsg(e.getMsg());
            return enumPojoDto;
        }).collect(Collectors.toList());
    }
}
