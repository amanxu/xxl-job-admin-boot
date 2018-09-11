package com.xxl.job.starter.properties;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@ToString
@ConfigurationProperties(prefix = "xxl.job")
@ConditionalOnProperty(value = "xxl.job.enabled", matchIfMissing = true)
public class XxlJobProperties {

	@NotBlank(message = "xxl.job.admin-addresses may be not blank.")
	private String adminAddresses;

	private String ip;

	private int port = 9999;

	private String accessToken;

	private String logPath = "/data/logs";

	private int logRetentionDays = 10;
}
