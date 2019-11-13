package com.czw.ms.auth.translator;

import com.czw.ms.common.entity.MsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 16:24
 * @UpdeteTime: 2019-10-08 16:24
 * @Description:
 */
@Slf4j
@Component
public class MsWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
	@Override
	public ResponseEntity translate(Exception e) throws Exception {
		ResponseEntity.BodyBuilder status=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		MsResponse response=new MsResponse();
		String message="认证失败";
		log.error(message,e);
		if (e instanceof UnsupportedGrantTypeException){
			message="不支持认证类型";
			return status.body(response.message(message));
		}
		if (e instanceof InvalidGrantException){
			if (StringUtils.containsIgnoreCase(e.getMessage(),"Invalid refresh token")){
				message="refresh token 无效";
				return status.body(response.message(message));
			}
			if (StringUtils.containsIgnoreCase(e.getMessage(),"locked")){
				message="用户以被锁定";
				return status.body(response.message(message));
			}
			message="用户名 或密码错误";
			return status.body(response.message(message));
		}
		return status.body(response.message(message));
	}
}
