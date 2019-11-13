package com.czw.ms.auth.configure;

import com.czw.ms.auth.properties.MsAuthProperties;
import com.czw.ms.auth.properties.MsClientsProperties;
import com.czw.ms.auth.service.MsUserDetailService;
import com.czw.ms.auth.translator.MsWebResponseExceptionTranslator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 09:37
 * @UpdeteTime: 2019-10-08 09:37
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class MsAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Autowired
	private MsUserDetailService userDetailService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MsAuthProperties authProperties;
	@Autowired
	private MsWebResponseExceptionTranslator translator;
	@Override
	public void configure(ClientDetailsServiceConfigurer clients)throws Exception{
		MsClientsProperties[] clientsArray=authProperties.getClients();
		InMemoryClientDetailsServiceBuilder builder=clients.inMemory();
		if (ArrayUtils.isNotEmpty(clientsArray)){
			for (MsClientsProperties client:clientsArray){
				if (StringUtils.isBlank(client.getClient())){
					throw new Exception("client不能为空");
				}
				if (StringUtils.isBlank(client.getSecret())){
					throw new Exception("secret不能为空");
				}
				System.out.println(client.getClient());
				System.out.println(client.getSecret());
				String[] grantTypes=StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(),",");
				builder.withClient(client.getClient())
						.secret(passwordEncoder.encode(client.getSecret()))
						.authorizedGrantTypes(grantTypes)
						.scopes(client.getScope());
			}
		}
	}
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer)throws Exception{
		endpointsConfigurer.tokenStore(tokenStore())
				.userDetailsService(userDetailService)
				.authenticationManager(authenticationManager)
				.tokenServices(defaultTokenServices())
				.exceptionTranslator(translator);
	}

	@Bean
	public TokenStore tokenStore(){
		return new RedisTokenStore(redisConnectionFactory);
	}
	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices(){
		DefaultTokenServices tokenServices=new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
		tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
		return tokenServices;
	}
}
