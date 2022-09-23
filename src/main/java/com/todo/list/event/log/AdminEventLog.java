package com.todo.list.event.log;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.todo.list.event.EventLog;

@Aspect
@Component
public class AdminEventLog extends EventLog {

	public AdminEventLog() {
		setEventLogger(getClass());
	}

	@Override
	public void record() {
		// TODO Auto-generated method stub

	}
}
