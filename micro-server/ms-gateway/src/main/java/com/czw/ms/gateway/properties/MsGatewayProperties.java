package com.czw.ms.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: czw
 * @CreateTime: 2019-10-16 10:24
 * @UpdeteTime: 2019-10-16 10:24
 * @Description:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:ms-gateway.properties"})
@ConfigurationProperties(prefix = "ms.gateway")
public class MsGatewayProperties {
	/**
	 * 禁止外部访问的uri，多个值的话以逗号隔开
	 */
	private String forbidRequestUri;
}
