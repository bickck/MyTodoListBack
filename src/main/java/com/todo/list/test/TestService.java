package com.todo.list.test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.hibernate.annotations.OptimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepository;

	// @Transactional(isolation = Isolation.SERIALIZABLE)
	public TestEntity testSaveService(String test) throws InterruptedException {
		if (testRepository.existsByTest(test)) {
			return null;
		}
		return testRepository.save(new TestEntity(test));
	}
}
