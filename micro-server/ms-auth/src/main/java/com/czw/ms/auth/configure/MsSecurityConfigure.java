package com.czw.ms.auth.configure;

import com.czw.ms.auth.filter.ValidateCodeFilter;
import com.czw.ms.auth.service.MsUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: czw
 * @CreateTime: 2019-10-07 15:25
 * @UpdeteTime: 2019-10-07 15:25
 * @Description:MsSecurityConfigure用于处理/oauth开头的请求，spring cloud oauth内部定义
 * 的获取令牌，刷新令牌的请求地址都是以/oauth开头的，也就是说这个类用于处理和令牌相关的请求
 */
//resource的优先级为3，security优先级为100，会导致/oauth/**被拦截，
// 所以提高优先级
@Order(2)
@EnableWebSecurity
public class MsSecurityConfigure extends WebSecurityConfigurerAdapter {
	@Autowired
	private MsUserDetailService userDetailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ValidateCodeFilter validateCodeFilter;
	@Bean
	public AuthenticationManager authenticationManagerBean()throws Exception{
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http
				//.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.requestMatchers()
				//只对"/oauth/**"开头的请求有效
				.antMatchers("/oauth/**")
				.and()
				.authorizeRequests()
				.antMatchers("/oauth/**")
				.authenticated()
				.and()
				.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
}
