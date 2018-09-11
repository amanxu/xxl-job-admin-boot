package com.xxl.job.admin.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-05 13:34
 */
@Setter
@Getter
@ToString
public class UserLoginDto implements Serializable{

    private String username;
    private String password;
    private Integer ifRemember;
}
