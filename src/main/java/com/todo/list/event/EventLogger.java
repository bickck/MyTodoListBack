package com.todo.list.event;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public interface EventLogger {
	
	public void setEventLogger(Class<?> clazz);
	
	public Logger getEventLogger();
	
	public Object record(ProceedingJoinPoint joinPoint) throws Throwable;

}
