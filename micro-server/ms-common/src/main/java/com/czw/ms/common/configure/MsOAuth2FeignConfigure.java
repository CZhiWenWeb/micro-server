package com.czw.ms.common.configure;

import com.czw.ms.common.entity.MsConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 10:55
 * @UpdeteTime: 2019-10-09 10:55
 * @Description:
 */
public class MsOAuth2FeignConfigure {
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor(){
		return requestTemplate -> {
			//添加Zuul Token
			String zuulToken=new String(Base64Utils.encode(MsConstant.ZUUL_TOKEN_VALUE.getBytes()));
			requestTemplate.header(MsConstant.ZUUL_TOKEN_HEADER,zuulToken);

			//添加头部
			Object details= SecurityContextHolder.getContext().getAuthentication().getDetails();
			if (details instanceof OAuth2AuthenticationDetails){
				String authorizationToken=((OAuth2AuthenticationDetails) details).getTokenValue();
				requestTemplate.header(HttpHeaders.AUTHORIZATION,"bearer"+authorizationToken);
			}
		};
	}
}
