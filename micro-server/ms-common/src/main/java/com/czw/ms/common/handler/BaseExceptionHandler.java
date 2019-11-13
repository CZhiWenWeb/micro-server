package com.czw.ms.common.handler;

import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.exception.MsAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 09:19
 * @UpdeteTime: 2019-10-09 09:19
 * @Description:
 */
@Slf4j
public class BaseExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public MsResponse handleException(Exception e){
		log.error("系统内部异常",e);
		return new MsResponse().message(e.getMessage());
	}
	@ExceptionHandler(value = MsAuthException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public MsResponse handleMsAuthException(MsAuthException e){
		log.error("系统错误",e);
		return new MsResponse().message(e.getMessage());
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public MsResponse handleAccessDeniedException(){
		return new MsResponse().message("没有权限访问资源");
	}

	/**
	 * 处理请求传参校验
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MsResponse handleBindException(BindException e){
		StringBuilder message=new StringBuilder();
		List<FieldError> fieldErrors=e.getBindingResult().getFieldErrors();
		for (FieldError error:fieldErrors
		     ) {
			message.append(error.getField()).append(error.getDefaultMessage()).append(",");
		}
		message=new StringBuilder(message.substring(0,message.length()-1));
		return new MsResponse().message(message.toString());
	}
}
