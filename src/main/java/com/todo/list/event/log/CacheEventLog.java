package com.todo.list.event.log;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.list.event.EventLog;
import com.todo.list.event.EventLogger;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
public class CacheEventLog extends EventLog{
	
//	@Autowired
//	private 
	
	public CacheEventLog() {
		super.setEventLogger(getClass());
	}

	@Override
	public void record() {
		// TODO Auto-generated method stub
		
	}
}
