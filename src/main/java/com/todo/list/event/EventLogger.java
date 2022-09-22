package com.todo.list.event;

import org.slf4j.Logger;

public interface EventLogger {
	
	public void setEventLogger(Class<?> clazz);
	
	public Logger getEventLogger();
	
	public void record();

}
