package com.todo.list.test.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("testCache")
public class RedisTestEntity {

	@Id
	private TestEntity testEntity;

	public RedisTestEntity() {
		// TODO Auto-generated constructor stub
	}

	public RedisTestEntity(TestEntity testEntity) {
		this.testEntity = testEntity;
	}

	public TestEntity getTestEntity() {
		return testEntity;
	}

	public void setTestEntity(TestEntity testEntity) {
		this.testEntity = testEntity;
	}

}
