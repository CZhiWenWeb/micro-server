package com.czw.ms.server.test;

import com.czw.ms.common.annotation.MsCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@EnableFeignClients
@MsCloudApplication
public class MsServerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsServerTestApplication.class, args);
	}

}
