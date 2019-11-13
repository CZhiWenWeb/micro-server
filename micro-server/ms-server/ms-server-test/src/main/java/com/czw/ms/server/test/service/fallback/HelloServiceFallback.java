package com.czw.ms.server.test.service.fallback;

import com.czw.ms.server.test.service.IHelloService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 10:49
 * @UpdeteTime: 2019-10-09 10:49
 * @Description:
 */
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
	@Override
	public IHelloService create(Throwable throwable) {
		return name -> {
			log.error("调用ms-server-system服务出错",throwable);
			return "调用出错";
		};
	}
}
