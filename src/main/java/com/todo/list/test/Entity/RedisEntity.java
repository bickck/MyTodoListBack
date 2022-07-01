package com.todo.list.test.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "eventId",timeToLive= 100)
public class RedisEntity {

	@Id
	private Long eventId;

	public RedisEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RedisRepositoryEntity [eventId=" + eventId + "]";
	}

	public RedisEntity(Long eventId) {
		super();
		this.eventId = eventId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	

}
