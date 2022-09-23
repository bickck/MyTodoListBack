package com.todo.list.event.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.todo.list.event.EventLogListener;

@Aspect
@Component
public class AdminEventLog {

	public AdminEventLog() {
		// TODO Auto-generated constructor stub
		
	}
}
