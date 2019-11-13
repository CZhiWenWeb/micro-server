package com.czw.ms.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.czw.ms.server.system.properties.MsServerSystemProperties;
import com.czw.ms.server.system.properties.MsSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 10:08
 * @UpdeteTime: 2019-10-14 10:08
 * @Description:
 */
@Configuration
@EnableSwagger2
public class MsWebConfigure {
	@Autowired
	private MsServerSystemProperties properties;
	@Bean
	public Docket swaggerApi(){
		MsSwaggerProperties swagger=properties.getSwagger();
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo(swagger))
				.securitySchemes(Collections.singletonList(securityScheme(swagger)))
				.securityContexts(Collections.singletonList(securityContext(swagger)));
	}

	private ApiInfo apiInfo(MsSwaggerProperties swagger){
		return new ApiInfo(
				swagger.getTitle(),
				swagger.getDescription(),
				swagger.getVersion(),
				null,
				new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
				swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
	}

	@Bean
	public PaginationInterceptor paginationInterceptor(){
		PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
		List<ISqlParser> sqlParserList=new ArrayList<>();
		sqlParserList.add(new BlockAttackSqlParser());
		paginationInterceptor.setSqlParserList(sqlParserList);
		return paginationInterceptor;
	}

	private SecurityScheme securityScheme(MsSwaggerProperties swagger){
		GrantType grantType=new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());
		return new OAuthBuilder().name(swagger.getName())
				.grantTypes(Collections.singletonList(grantType))
				.scopes(Arrays.asList(scopes(swagger)))
				.build();
	}

	private SecurityContext securityContext(MsSwaggerProperties swagger){
		return SecurityContext.builder()
				.securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(),scopes(swagger))))
				.forPaths(PathSelectors.any())
				.build();
	}

	private AuthorizationScope[] scopes(MsSwaggerProperties swagger){
		return new AuthorizationScope[]{
				new AuthorizationScope(swagger.getScope(),"")
		};
	}
}
