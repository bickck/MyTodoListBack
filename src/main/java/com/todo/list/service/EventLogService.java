package com.todo.list.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.entity.log.EventLogRecordEntity;
import com.todo.list.repository.event.EventLogRepository;

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
	private EventLogRepository eventLogRepository;

	@Transactional
	public void save(EventLogRecordEntity eventLogRecordEntity) {
		System.out.println("save");
		System.out.println(eventLogRecordEntity.toString());
		eventLogRepository.save(eventLogRecordEntity);
	}

	public void select() {

	}

}
