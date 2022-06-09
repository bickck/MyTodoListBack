package com.todo.list.configs.cache;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@EnableRedisRepositories
@Configuration
public class CacheConfig implements CachingConfigurer {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisProperties properties;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(properties.getHost(), properties.getPort());
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager.RedisCacheManagerBuilder cacheManager = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory());

		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()))
				.entryTtl(Duration.ofMinutes(30));
		
		//new GenericJackson2JsonRedisSerializer();
		//RedisSerializer.java()
		cacheManager.withCacheConfiguration("todoCache", cacheConfiguration);
		cacheManager.withCacheConfiguration("quoteCache", cacheConfiguration);
		cacheManager.withCacheConfiguration("testCache", cacheConfiguration);
		return cacheManager.build();
	}

//	@Bean(destroyMethod = "shutdown")
//	public net.sf.ehcache.CacheManager ehcacheManager() {
//		CacheConfiguration cacheConfiguration = new CacheConfiguration();
//		cacheConfiguration.setName("cacheStorage");
//		cacheConfiguration.setMemoryStoreEvictionPolicyFromObject(MemoryStoreEvictionPolicy.LRU);
//		cacheConfiguration.setMaxBytesLocalHeap((long) 1000);
//		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
//		config.addCache(cacheConfiguration);
//
//		return net.sf.ehcache.CacheManager.newInstance(config);
//	}
//
//	@Override
//	public CacheManager cacheManager() {
//		// TODO Auto-generated method stub
//		return new EhCacheCacheManager(ehcacheManager());
//	}
//
//	@Override
//	public CacheResolver cacheResolver() {
//		// TODO Auto-generated method stub
//		return new SimpleCacheResolver(cacheManager());
//	}
//
//	@Override
//	public CacheErrorHandler errorHandler() {
//		// TODO Auto-generated method stub
//		return CachingConfigurer.super.errorHandler();
//	}
//
//	@Override
//	public KeyGenerator keyGenerator() {
//		// TODO Auto-generated method stub
//		return CachingConfigurer.super.keyGenerator();
//	}

//	@Bean
//	public CacheManager cacheManager() {
//
//		CachingProvider provider = Caching.getCachingProvider();
//		CacheManager cacheManager = provider.getCacheManager();
//
//		CacheConfigurationBuilder<String, String> configurationBuilder = CacheConfigurationBuilder
//				.newCacheConfigurationBuilder(String.class, String.class,
//
//						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(1, MemoryUnit.MB))
//				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(1)));
//
//		javax.cache.configuration.Configuration<String, String> configuration = Eh107Configuration
//				.fromEhcacheCacheConfiguration(configurationBuilder);
//
//		cacheManager.createCache("cacheStorage", configuration);
//
////		SimpleCacheManager cacheManager1 = new SimpleCacheManager().;
////		cacheManager1.setCaches(List.of(new ConcurrentMapCache("cacheStorage")))
////		
//
//		return cacheManager;
//	}

}
