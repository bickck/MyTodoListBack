package com.todo.list.event.log;

import com.todo.list.event.EventLog;

public class AdminEventLog extends EventLog{

	public AdminEventLog() {
		setEventLogger(getClass());
	}

	@Override
	public void record() {
		// TODO Auto-generated method stub
		
	}
}
