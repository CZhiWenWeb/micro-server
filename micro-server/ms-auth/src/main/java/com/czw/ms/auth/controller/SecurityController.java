package com.czw.ms.auth.controller;

import com.czw.ms.auth.service.ValidateCodeService;
import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.exception.MsAuthException;
import com.czw.ms.common.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 08:52
 * @UpdeteTime: 2019-10-08 08:52
 * @Description:
 */
@RestController
public class SecurityController {
	@Autowired
	private ConsumerTokenServices consumerTokenServices;
	@Autowired
	private ValidateCodeService validateCodeService;

	@GetMapping("oauth/test")
	public String testOauth(){
		return "oauth";
	}
	@GetMapping("user")
	public Principal currentUser(Principal principal){
		System.out.println(principal.getName());
		return principal;
	}
	@DeleteMapping("signout")
	public MsResponse signout(HttpServletRequest request)throws MsAuthException{
		String authorization=request.getHeader("Authorization");
		String token= StringUtils.replace(authorization,"bearer","");
		MsResponse msResponse=new MsResponse();
		if (!consumerTokenServices.revokeToken(token)){
			throw new MsAuthException("退出登入失败");
		}
		return msResponse.message("退出登入成功");
	}

	@GetMapping("captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
		validateCodeService.create(request,response);
	}
}
