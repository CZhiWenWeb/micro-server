package com.czw.ms.common.exception;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 16:30
 * @UpdeteTime: 2019-10-10 16:30
 * @Description:
 */
public class ValidateCodeException extends Exception{
	private static final long serialVersionUID = 569434310627929256L;
	public ValidateCodeException(String message){
		super(message);
	}
}
