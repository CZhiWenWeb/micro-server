package com.czw.ms.auth.filter;

import com.czw.ms.auth.service.ValidateCodeService;
import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.exception.ValidateCodeException;
import com.czw.ms.common.utils.FebsUtil;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 17:22
 * @UpdeteTime: 2019-10-10 17:22
 * @Description:
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
	@Autowired
	private ValidateCodeService validateCodeService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		log.info("ValidateCodeFilter start");
		String header=request.getHeader("Authorization");
		String clientId=getClientId(header,request);
		RequestMatcher matcher=new AntPathRequestMatcher("/oauth/token", Request.HttpMethod.POST.toString());
		if (matcher.matches(request)&&
				StringUtils.equalsIgnoreCase(request.getParameter("grant_type"),"password")
		&&!StringUtils.equalsAnyIgnoreCase(clientId,"swagger")){
			try{
				validateCode(request);
				filterChain.doFilter(request,response);
			}catch (ValidateCodeException e){
				MsResponse msResponse=new MsResponse();
				FebsUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE,HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						msResponse.message(e.getMessage()));
				log.error(e.getMessage(),e);
			}
		}else {
			filterChain.doFilter(request,response);
		}
	}

	private void validateCode(HttpServletRequest request) throws ValidateCodeException {
		String code=request.getParameter("code");
		String key=request.getParameter("key");
		validateCodeService.check(key,code);
	}


	private String getClientId(String header, HttpServletRequest request) {
		String clientId = "";
		try {
			byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
			byte[] decoded;
			decoded = Base64.getDecoder().decode(base64Token);

			String token = new String(decoded, StandardCharsets.UTF_8);
			int delim = token.indexOf(":");
			if (delim != -1) {
				clientId = new String[]{token.substring(0, delim), token.substring(delim + 1)}[0];
			}
		} catch (Exception ignore) {
		}
		return clientId;
	}
}
