package com.czw.ms.server.test.configure;

import com.czw.ms.common.handler.MsAccessDeniedHandler;
import com.czw.ms.common.handler.MsAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 14:25
 * @UpdeteTime: 2019-10-08 14:25
 * @Description:
 */
@EnableResourceServer
@Configuration
public class MsServerTestResourceServerConfigure extends ResourceServerConfigurerAdapter {
	@Autowired
	private MsAuthExceptionEntryPoint exceptionEntryPoint;

	@Autowired
	private MsAccessDeniedHandler accessDeniedHandler;

	@Override
	public void configure(HttpSecurity http)throws Exception{
		http.csrf().disable()
				.requestMatchers()
				.antMatchers("/**")
				.and()
				.authorizeRequests()
				.antMatchers("/**")
				.authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources){
		resources.authenticationEntryPoint(exceptionEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);
	}
}
