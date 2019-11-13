package com.czw.ms.server.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 14:12
 * @UpdeteTime: 2019-10-08 14:12
 * @Description:
 */
@RestController
public class TestController {
	@GetMapping("info")
	public String test(){
		return "ms-server-system";
	}
	@GetMapping("currentUser")
	public Principal currentUser(Principal principal){
		return principal;
	}

	@GetMapping("hello")
	public String hello(String name) {
		return "hello" + name;
	}
}
