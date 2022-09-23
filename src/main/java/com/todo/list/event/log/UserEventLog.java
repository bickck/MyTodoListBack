package com.todo.list.event.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.event.EventLog;
import com.todo.list.event.EventLogger;

@Aspect
@Component
public class UserEventLog extends EventLog{

	private final String USER_EVENT_LOG_EXECUTION = "execution(* com.todo.list.service.*.User*.*())";
	
	public UserEventLog() {
		super.setEventLogger(getClass());
	}

	@Pointcut(USER_EVENT_LOG_EXECUTION)
	public void userExecution() {

	}

	@Around(value = "userCrudExecution()")
	public Object userAccessLog(ProceedingJoinPoint joinPoint) throws Throwable {

//		logger.info("Current Method = {}", joinPoint.getTarget());

		return joinPoint.proceed();
	}

	@Override
	public void record() {
		// TODO Auto-generated method stub
		
	}

	
}
