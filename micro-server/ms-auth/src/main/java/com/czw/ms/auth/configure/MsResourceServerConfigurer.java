package com.czw.ms.auth.configure;

import com.czw.ms.auth.properties.MsAuthProperties;
import com.czw.ms.common.handler.MsAccessDeniedHandler;
import com.czw.ms.common.handler.MsAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 09:15
 * @UpdeteTime: 2019-10-08 09:15
 * @Description:MsResourceServerConfigurer用于处理和令牌相关的请求，主要用于资源的保护，客户端
 * 只能通过oauth2协议发送的令牌从资源服务器中获取受保护的资源
 */
@EnableResourceServer
@Configuration
public class MsResourceServerConfigurer extends ResourceServerConfigurerAdapter {
	@Autowired
	private MsAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private MsAuthExceptionEntryPoint exceptionEntryPoint;
	@Autowired
	private MsAuthProperties properties;

	@Override
	public void configure(HttpSecurity http)throws Exception{
		String[] anonUrls= StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(),",");
		http.csrf().disable()
				.requestMatchers().antMatchers("/**")
				.and()
				.authorizeRequests()
				.antMatchers(anonUrls).permitAll()
				.antMatchers("/actuator/**").permitAll()
				.antMatchers("/**").authenticated()
				.and().httpBasic();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources){
		resources.authenticationEntryPoint(exceptionEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);
	}
}
