package com.todo.list.util.log;

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

@Aspect
@Component
public class UserCRUDEventLog {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.todo.list.service.*.User*.*())")
	public void userCrudExecution() {

	}

	@Around(value = "userCrudExecution()")
	public Object userAccessLog(ProceedingJoinPoint joinPoint) throws Throwable {

		logger.info("Current Method = {}", joinPoint.getTarget());

		return joinPoint.proceed();
	}
}
