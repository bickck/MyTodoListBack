package com.todo.list.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.todo.list.entity.log.EventLogRecordEntity;
import com.todo.list.repository.event.EventLoggerRepository;

@Service
public class EventLogService {

//	순환 참조 문제 발생
//	@Autowired
//	private EventLogService eventLogService;
//
//	@Transactional
//	public void save(EventLogRecordEntity eventLogRecordEntity) {
//		eventLogService.save(eventLogRecordEntity);
//	}
//
//	public void select() {
//		
//	}

	@Autowired
	private EventLoggerRepository eventLogRepository;

	//@Transactional
	@Async
	public void saveLogger(EventLogRecordEntity eventLogRecordEntity) {
		System.out.println("save");
		System.out.println(eventLogRecordEntity.toString());
		eventLogRepository.save(eventLogRecordEntity);
	}

	public void select() {

	}

}
