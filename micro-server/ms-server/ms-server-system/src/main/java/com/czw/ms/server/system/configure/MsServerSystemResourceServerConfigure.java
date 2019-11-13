package com.czw.ms.server.system.configure;

import com.czw.ms.common.handler.MsAccessDeniedHandler;
import com.czw.ms.common.handler.MsAuthExceptionEntryPoint;
import com.czw.ms.server.system.properties.MsServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 14:03
 * @UpdeteTime: 2019-10-08 14:03
 * @Description:
 */
@Configuration
@EnableResourceServer
public class MsServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {
	@Autowired
	private MsAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private MsAuthExceptionEntryPoint exceptionEntryPoint;

	@Autowired
	private MsServerSystemProperties properties;
	@Override
	public void configure(HttpSecurity http)throws Exception{
		String[] anonUrls= StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(),",");

		http.csrf().disable()
				.requestMatchers()
				.antMatchers("/**")
				.and()
				.authorizeRequests()
				.antMatchers(anonUrls).permitAll()
				.antMatchers("/actuator/**").permitAll()
				.antMatchers("/**")
				.authenticated();
	}
	@Override
	public void configure(ResourceServerSecurityConfigurer resources){
		resources.accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(exceptionEntryPoint);
	}
}
