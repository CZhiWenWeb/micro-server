package com.czw.ms.gateway.controller;

import com.czw.ms.common.entity.MsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: czw
 * @CreateTime: 2019-11-11 14:23
 * @UpdeteTime: 2019-11-11 14:23
 * @Description:
 */
@RestController
public class TestController {
	@RequestMapping("fallback/{name}")
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Mono<MsResponse> systemFallback(@PathVariable String name){
		String response=String.format("访问超时或服务不可用",name);
		return Mono.just(new MsResponse().message(response));
	}
}
