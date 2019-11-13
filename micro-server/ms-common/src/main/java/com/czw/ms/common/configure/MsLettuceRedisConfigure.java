package com.czw.ms.common.configure;

import com.czw.ms.common.service.RedisService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 14:43
 * @UpdeteTime: 2019-10-10 14:43
 * @Description:
 */
public class MsLettuceRedisConfigure {

	@Bean
	//当项目中存在RedisOperations类的时候，自定义的RedisTemplate Bean才会被注册
	//到IOC容器中
	@ConditionalOnClass(RedisOperations.class)
	public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String,Object> template=new RedisTemplate<>();
		template.setConnectionFactory(factory);

		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper mapper=new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(mapper);

		StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
		// key采用 String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的 key也采用 String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用 jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的 value序列化方式采用 jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();

		return template;

	}

	@Bean
	//IOC容器中存在名称为redisTemplate的Bean，将自定义Bean注入到IOC容器中
	@ConditionalOnBean(name = "redisTemplate")
	public RedisService redisServer(){
		return new RedisService();
	}
}
