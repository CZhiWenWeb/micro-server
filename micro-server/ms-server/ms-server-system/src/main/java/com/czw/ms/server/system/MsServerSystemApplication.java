package com.czw.ms.server.system;

import com.czw.ms.common.annotation.MsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
//开启spring cloud security权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MsCloudApplication
@EnableTransactionManagement
@MapperScan("com.czw.ms.server.system.mapper")
public class MsServerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsServerSystemApplication.class, args);
	}

}
