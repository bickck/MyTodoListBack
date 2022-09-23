package com.todo.list.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.log.EventLogRecordEntity;

public interface EventLogRepository extends JpaRepository<EventLogRecordEntity, Long> {

}
