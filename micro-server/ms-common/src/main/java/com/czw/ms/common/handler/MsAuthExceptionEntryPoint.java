package com.czw.ms.common.handler;

import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.utils.FebsUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 16:40
 * @UpdeteTime: 2019-10-08 16:40
 * @Description:
 */
public class MsAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		MsResponse msResponse=new MsResponse();
		FebsUtil.makeResponse(response,MediaType.APPLICATION_JSON_UTF8_VALUE,HttpServletResponse.SC_UNAUTHORIZED,
				msResponse.message("token无效"));
	}
}
