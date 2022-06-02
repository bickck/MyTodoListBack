package com.todo.list.test;

import org.hibernate.boot.model.Caching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.EhCache;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import com.todo.list.configs.cache.CacheConfig;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

@RestController
public class TestCacheController {

	@Autowired
	private TestRepository repository;
	

	@GetMapping("/test/cache/{id}")
	@Cacheable(cacheNames = "cacheStorage", key = "#id")
	public TestEntity testCacheEventLogMethod(@PathVariable Long id) {
		
		return repository.findById(id).get();
	}

	@GetMapping("/test/cachememory")
	public void testCacheMemory() {
		net.sf.ehcache.CacheManager cacheCacheManager = net.sf.ehcache.CacheManager.create();
		
		
		System.out.println(cacheCacheManager.getOriginalConfigurationText());
		System.out.println(cacheCacheManager.getCache("cacheStorage"));
		System.out.println(cacheCacheManager.getActiveConfigurationText());
		System.out.println(cacheCacheManager.getCacheNames());
		System.out.println(cacheCacheManager.getCacheManagerEventListener());
		//System.out.println(cacheCacheManager.getOriginalConfigurationText());
	}
}
