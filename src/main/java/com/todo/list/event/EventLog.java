package com.todo.list.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EventLog implements EventLogger {

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
