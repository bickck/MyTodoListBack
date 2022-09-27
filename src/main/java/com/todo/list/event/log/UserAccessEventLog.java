package com.todo.list.event.log;

import java.util.Date;

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

import com.todo.list.entity.log.EventLogRecordEntity;
import com.todo.list.event.EventLogListener;
import com.todo.list.event.record.EventLogCallee;

@Aspect
@Component
public class UserAccessEventLog extends EventLogListener {

	private final static String CLIENT_ACCESS_EXECUTION = "execution(* com.todo.list.*.*..*(@UserAuthToken))";

	@Autowired
	private EventLogCallee eventLogCallee;
	
	public UserAccessEventLog() {
		super.setEventLogger(getClass());
	}

	@Pointcut(CLIENT_ACCESS_EXECUTION)
	public void accessEventLogExecution() {
		super.getEventLogger().info("call accessEventLogExecution aop");
	}

	@Override
	@Around(value = "accessEventLogExecution()")
	public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
		// TODO Auto-generated method stub
		EventLogRecordEntity entity = new EventLogRecordEntity();
		entity.setLogMessage("");
		entity.setDateOfOccurrence(new Date().toString());
		entity.setMethodOfOccurrence(joinPoint.getSignature().toString());
		entity.setLocationOfOccurrence(joinPoint.getSourceLocation().toString());
		
		return joinPoint.proceed();
	}

}
