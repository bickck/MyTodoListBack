package com.todo.list.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import com.todo.list.entity.log.EventLogRecordEntity;

public interface EventLogRepository extends JpaRepository<EventLogRecordEntity, Long> {

	//@Async 비동기 방식
	public EventLogRecordEntity save(EventLogRecordEntity entity);
}
