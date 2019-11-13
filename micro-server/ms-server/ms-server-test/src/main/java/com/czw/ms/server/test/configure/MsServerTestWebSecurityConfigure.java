package com.czw.ms.server.test.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: czw
 * @CreateTime: 2019-10-18 10:41
 * @UpdeteTime: 2019-10-18 10:41
 * @Description:
 */
@EnableWebSecurity
public class MsServerTestWebSecurityConfigure extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().and()
				.authorizeRequests().antMatchers("/actuator/**").permitAll();
	}
}
