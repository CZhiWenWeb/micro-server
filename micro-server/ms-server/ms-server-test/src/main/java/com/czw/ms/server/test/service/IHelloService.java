package com.czw.ms.server.test.service;

import com.czw.ms.common.entity.MsServerConstant;
import com.czw.ms.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 10:34
 * @UpdeteTime: 2019-10-09 10:34
 * @Description:
 */
@FeignClient(value =MsServerConstant.MS_SERVER_SYSTEM ,name = MsServerConstant.MS_SERVER_SYSTEM,contextId = "helloServiceClient",fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {
	@GetMapping("hello")
	String hello(@RequestParam String name);
}
