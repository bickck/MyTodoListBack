package com.todo.list.event.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.event.EventLog;

@Aspect
@Component
public class UserAccessEventLog extends EventLog{
	
	private final static String CLIENT_ACCESS_EXECUTION = "execution(* com.todo.list.controller.*..*())";

	
	public UserAccessEventLog() {
		super.setEventLogger(getClass());
	}
	

	@Pointcut(CLIENT_ACCESS_EXECUTION)
	public void accessEventLogExecution() {

	}

	@Around(value = "accessEventLogExecution()")
	public Object clientAccessLog(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

//		logger.trace("Protocol = {}", httpServletRequest.getProtocol());
//		logger.info("getRequestURI = {}", httpServletRequest.getRequestURI());
//		logger.info("Access Method = {} , {}", joinPoint.getThis(), joinPoint.getSignature());

		return joinPoint.proceed();
	}


	@Override
	public void record() {
		// TODO Auto-generated method stub
		
	}

}
