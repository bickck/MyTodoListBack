package com.todo.list.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.todo.list.event.record.EventLogCallee;

public abstract class EventLogListener implements EventLogger {

	private Logger logger;


	@Override
	public void setEventLogger(Class<?> clazz) {
		// TODO Auto-generated method stub'
		logger = LoggerFactory.getLogger(clazz);
	}

	@Override
	public Logger getEventLogger() {
		// TODO Auto-generated method stub
		return this.logger;
	}

}
