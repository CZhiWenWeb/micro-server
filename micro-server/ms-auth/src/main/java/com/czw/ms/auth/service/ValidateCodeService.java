package com.czw.ms.auth.service;

import com.czw.ms.auth.properties.MsAuthProperties;
import com.czw.ms.auth.properties.MsValidateCodeProperties;
import com.czw.ms.common.entity.MsConstant;
import com.czw.ms.common.exception.ValidateCodeException;
import com.czw.ms.common.service.RedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 16:31
 * @UpdeteTime: 2019-10-10 16:31
 * @Description:
 */
@Service
public class ValidateCodeService {
	@Autowired
	private RedisService redisService;

	@Autowired
	private MsAuthProperties properties;

	public void create(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
		String key=request.getParameter("key");
		if (StringUtils.isBlank(key)){
			throw new ValidateCodeException("验证码key不能为空");
		}
		MsValidateCodeProperties code=properties.getCode();
		setHeader(response,code.getType());

		Captcha captcha=createCaptcha(code);
		redisService.set(MsConstant.CODE_PREFIX+key,StringUtils.lowerCase(captcha.text()),code.getTime());
		captcha.out(response.getOutputStream());
	}

	/**
	 * check code
	 * @param key
	 * @param value
	 */
	public void check(String key,String value) throws ValidateCodeException {
		Object codeInRedis=redisService.get(MsConstant.CODE_PREFIX+key);
		if (StringUtils.isBlank(value)){
			throw new ValidateCodeException("请输入验证码");
		}
		if (codeInRedis==null){
			throw new ValidateCodeException("验证码已过期");
		}
		if (!StringUtils.equalsIgnoreCase(value,String.valueOf(codeInRedis))){
			throw new ValidateCodeException("验证码错误");
		}
	}

	private Captcha createCaptcha(MsValidateCodeProperties code){
		Captcha captcha = null;
		if (StringUtils.equalsIgnoreCase(code.getType(), MsConstant.GIF)) {
			captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
		} else {
			captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
		}
		captcha.setCharType(code.getCharType());
		return captcha;
	}

	private void setHeader(HttpServletResponse response,String type){
		if (StringUtils.equalsIgnoreCase(type, MsConstant.GIF)){
			response.setContentType(MediaType.IMAGE_GIF_VALUE);
		}else {
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
		}
		response.setHeader(HttpHeaders.PRAGMA,"No-cache");
		response.setHeader(HttpHeaders.CACHE_CONTROL,"No-cache");
		response.setDateHeader(HttpHeaders.EXPIRES,0L);
	}
}
