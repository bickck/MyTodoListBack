package com.todo.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import com.todo.list.test.Entity.RedisEntity;
import com.todo.list.test.Entity.RedisTestEntity;
import com.todo.list.test.Entity.TestEntity;
import com.todo.list.test.repository.EventRepository;
import com.todo.list.test.repository.RedisTestRepository;
import com.todo.list.test.repository.TestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConnectinoTest {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private RedisTestRepository redisTestRepository;

	@Autowired
	private RedisCacheManager redisCacheManager;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	// @Test
	public void dbSize() {
		// redisTemplate.
	}

	// @Test
	public void testBatchSave() {
		for (int i = 0; i < 10000; i++) {
			long id = i;
			eventRepository.save(new RedisEntity(id));
		}
	}

	// @Test
	public void executePipeLineTest() {

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("1", "TestDateA");
		maps.put("2", "TestDateB");
		redisTemplate.getExpire(maps);

		redisTemplate.executePipelined(new RedisCallback<Object>() {
			RedisSerializer keySerializer = redisTemplate.getKeySerializer();
			RedisSerializer valueSerializer = redisTemplate.getValueSerializer();

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub

				connection.rPush(keySerializer.serialize("2"), valueSerializer.serialize(maps.get("2")));

				System.out.println(connection.dbSize());
				return null;
			}

		});
	}

	@Test
	public void delete() {
		List<RedisClientInfo> redis = redisTemplate.getClientList();	
		redis.forEach((data)-> {
			System.out.println(data.toString());
		});
	}

	// @Test
	public void testRedisGetKeySerializer() {
		System.out.println(redisTemplate.getKeySerializer().getTargetType());
	}

	// @Test
	public void testRedisGetValueSerializer() {
		System.out.println(redisTemplate.getValueSerializer().getTargetType());
	}

	// @Test
	public void testBatchDelete() {
		eventRepository.deleteAll();
	}

	// @Test
	public void testRedisRepositoryInsertJob() {
		long eventId = 1;
		eventRepository.save(new RedisEntity(eventId));
	}

	// @Test
	public void testRedisManagerNames() {
		redisCacheManager.getCacheNames().forEach((data) -> {
			System.out.println(data);
		});
	}

	@Test
	public void testRedisDelete() {
		long Id = 1;
		eventRepository.delete(new RedisEntity(Id));
	}

	// @Test
	public void testRedisManagerName() {
		String redisManagerName = RedisCacheManagerName.TodoCacheManagerName;
		Cache cache = redisCacheManager.getCache(redisManagerName);
		// cache.put("2", "test");
		ValueWrapper valueWrapper = cache.get("2");
		System.out.println(valueWrapper.get().toString());
	}

	// @Test
	public void testRedisManagerDelete() {
		String redisManagerName = RedisCacheManagerName.TodoCacheManagerName;
		Cache cache = redisCacheManager.getCache(redisManagerName);
		cache.evict("2");
	}

	// @Test
	public void testRedisCacheName() {
		String redisCacheName = RedisCacheNames.TestCacheName;
		Cache cache = redisCacheManager.getCache(redisCacheName);
		ValueWrapper valueWrapper = cache.get("1");
		System.out.println(valueWrapper.get().toString());
	}

	// @Test
	public void testRedisCacheNameDelete() {
		String redisCacheName = RedisCacheNames.TestCacheName;
		Cache cache = redisCacheManager.getCache(redisCacheName);
		// cache.
		ValueWrapper valueWrapper = cache.get("1");
		System.out.println(valueWrapper.get().toString());
	}

	// @Test
	public void testRedisRepositorySelectJob() {
		eventRepository.findAll().forEach((data) -> {
			System.out.println("hi");
			System.out.println(data);
		});

	}

	// @Test
	public void testRedisRepositorydeleteJob() {
		long eventId = 1;
		eventRepository.delete(new RedisEntity(eventId));
	}

	// @Test
	public void testTestInsertRedisTestEntity() {
//		long id = 2;
//		redisTestRepository.save(new RedisTestEntity(new TestEntity(id)));
	}

	// @Test
	public void testTestCachefindAll() {
		Iterable<RedisTestEntity> itr = redisTestRepository.findAll();

		System.out.println(itr);
	}
}
