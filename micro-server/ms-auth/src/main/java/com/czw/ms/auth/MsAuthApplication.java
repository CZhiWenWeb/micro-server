package com.czw.ms.auth;

import com.czw.ms.common.annotation.EnableMsLettuceRedis;
import com.czw.ms.common.annotation.MsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MsCloudApplication
@MapperScan("com.czw.ms.auth.mapper")
@EnableMsLettuceRedis
public class MsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthApplication.class, args);
	}

}
