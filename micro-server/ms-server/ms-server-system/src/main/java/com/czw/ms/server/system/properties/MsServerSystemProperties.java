package com.czw.ms.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: czw
 * @CreateTime: 2019-10-15 08:47
 * @UpdeteTime: 2019-10-15 08:47
 * @Description:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:ms-server-system.properties"})
@ConfigurationProperties(prefix = "ms.server.system")
public class MsServerSystemProperties {
	/**
	 * 免认证url，多个值的话以逗号隔开
	 */
	private String anonUrl;
	private MsSwaggerProperties swagger=new MsSwaggerProperties();
}
