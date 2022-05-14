package com.todo.list.test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.hibernate.annotations.OptimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepository;

//	@PostConstruct
//	public void init() {
//		testRepository.save(new TestEntity("test-String"));
//	}

	// @Transactional(isolation = Isolation.SERIALIZABLE)
	public TestEntity testSaveService(String test) throws InterruptedException {
		if (testRepository.existsByTest(test)) {
			return null;
		}
		return testRepository.save(new TestEntity(test));
	}

	@Cacheable(value = "cacheStorage", key = "#no")
	public String testCacheService(Long no) {
		String test = testRepository.getById(no).getTest();
		return test;
	}

}
