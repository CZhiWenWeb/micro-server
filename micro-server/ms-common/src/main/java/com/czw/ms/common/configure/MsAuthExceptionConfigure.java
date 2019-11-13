package com.czw.ms.common.configure;

import com.czw.ms.common.handler.MsAccessDeniedHandler;
import com.czw.ms.common.handler.MsAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 17:31
 * @UpdeteTime: 2019-10-08 17:31
 * @Description:
 */
public class MsAuthExceptionConfigure {
	@Bean
	@ConditionalOnMissingBean(name = "accessDeniedHandler")
	public MsAccessDeniedHandler accessDeniedHandler(){
		return new MsAccessDeniedHandler();
	}
	@Bean
	@ConditionalOnMissingBean(name = "authenticationEntryPoint")
	public MsAuthExceptionEntryPoint authExceptionEntryPoint(){
		return new MsAuthExceptionEntryPoint();
	}
}
