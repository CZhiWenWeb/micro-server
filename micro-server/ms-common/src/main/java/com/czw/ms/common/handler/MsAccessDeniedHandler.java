package com.czw.ms.common.handler;

import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.utils.FebsUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 17:25
 * @UpdeteTime: 2019-10-08 17:25
 * @Description:
 */
public class MsAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		MsResponse msResponse=new MsResponse();
		FebsUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE,HttpServletResponse.SC_FORBIDDEN,
				msResponse.message("没有权限访问"));
	}
}
