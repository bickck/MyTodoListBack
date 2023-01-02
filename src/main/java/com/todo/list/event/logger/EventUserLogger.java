package com.todo.list.event.logger;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.entity.log.EventLogRecordEntity;
import com.todo.list.event.EventLogListener;
import com.todo.list.event.EventLogger;
import com.todo.list.event.record.EventLogCallee;

@Aspect
@Component
public class EventUserLogger extends EventLogListener {

	private final static String USER_EVENT_LOG_EXECUTION = "execution(* com.todo.list.service.*.User*.*())";

	@Autowired
	private EventLogCallee eventLogCallee;

	public EventUserLogger() {
		super.setEventLogger(getClass());
	}

	@Pointcut(USER_EVENT_LOG_EXECUTION)
	public void userExecution() {
		super.getEventLogger().info("call UserEventLog aop");
	}

	@Override
	@Around(value = "userExecution()")
	public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
		// TODO Auto-generated method stub

		EventLogRecordEntity entity = new EventLogRecordEntity();
		entity.setLogMessage("");
		entity.setDateOfOccurrence(new Date().toString());
		entity.setMethodOfOccurrence(joinPoint.getSignature().toString());
		entity.setLocationOfOccurrence(joinPoint.getSourceLocation().toString());
		eventLogCallee.callee(entity); // 비동기방식으로 로그를 DB에 저장
		return joinPoint.proceed();
	}

}
