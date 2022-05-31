package com.todo.list.configs.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

@EnableCaching
@Configuration
public class CacheConfig implements CachingConfigurer {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehcacheManager() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName("cacheStorage");
		cacheConfiguration.setMemoryStoreEvictionPolicyFromObject(MemoryStoreEvictionPolicy.LRU);
		cacheConfiguration.setMaxBytesLocalHeap((long) 1000);
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfiguration);

		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Override
	public CacheManager cacheManager() {
		// TODO Auto-generated method stub
		return new EhCacheCacheManager(ehcacheManager());
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return new SimpleCacheResolver(cacheManager());
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return CachingConfigurer.super.errorHandler();
	}

	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return CachingConfigurer.super.keyGenerator();
	}

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
