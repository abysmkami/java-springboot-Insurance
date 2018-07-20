package com.Insurance.ADemo;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
	
	@Bean RedisTemplate<String, String>redisTemplate(RedisConnectionFactory factory)
	{
		RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		template.setConnectionFactory(jedisConnectionFactory());
		
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
}
