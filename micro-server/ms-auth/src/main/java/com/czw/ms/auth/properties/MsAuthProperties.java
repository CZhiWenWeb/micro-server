package com.czw.ms.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 15:11
 * @UpdeteTime: 2019-10-08 15:11
 * @Description:
 */
@Data
//@Component的派生类，将MsAuthProperties注入到IOC容器中，且继承@Configuration
@SpringBootConfiguration
@PropertySource(value = {"classpath:ms-auth.properties"})
@ConfigurationProperties(prefix = "ms.auth")
public class MsAuthProperties {
	private MsClientsProperties[] clients={};
	private int accessTokenValiditySeconds=60*60*24;
	private int refreshTokenValiditySeconds=60*60*24*7;
	//免认证路径
	private String anonUrl;
	private MsValidateCodeProperties code=new MsValidateCodeProperties();
}
