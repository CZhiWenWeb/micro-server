package com.czw.ms.common.interceptor;

import com.czw.ms.common.entity.MsConstant;
import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.utils.FebsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 11:21
 * @UpdeteTime: 2019-10-09 11:21
 * @Description:
 */
public class MsServerProtectInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
		String token=request.getHeader(MsConstant.ZUUL_TOKEN_HEADER);
		String zuulToken=new String(Base64Utils.encode(MsConstant.ZUUL_TOKEN_VALUE.getBytes()));

		if (StringUtils.equals(zuulToken,token)){
			return true;
		}else {
			MsResponse msResponse=new MsResponse();
			FebsUtil.makeResponse(response,MediaType.APPLICATION_JSON_UTF8_VALUE,
					HttpServletResponse.SC_FORBIDDEN, msResponse.message("请通过网关获取资源"));
			return false;
		}
	}
}
