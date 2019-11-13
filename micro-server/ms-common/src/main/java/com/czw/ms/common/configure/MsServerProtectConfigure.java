package com.czw.ms.common.configure;

import com.czw.ms.common.interceptor.MsServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 11:29
 * @UpdeteTime: 2019-10-09 11:29
 * @Description:
 */
public class MsServerProtectConfigure implements WebMvcConfigurer {

	@Bean
	public HandlerInterceptor msServerProtectInterceptor(){
		return new MsServerProtectInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(msServerProtectInterceptor());
	}

	@Bean
	@ConditionalOnMissingBean(value = PasswordEncoder.class)
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
