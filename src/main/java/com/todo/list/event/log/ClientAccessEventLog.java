package com.todo.list.event.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ClientAccessEventLog {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.todo.list.controller.*..*())")
	public void accessExecution() {

	}

	@Around(value = "accessExecution()")
	public Object userAccessLog(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		logger.trace("Protocol = {}", httpServletRequest.getProtocol());
		logger.info("getRequestURI = {}", httpServletRequest.getRequestURI());
		logger.info("Access Method = {} , {}", joinPoint.getThis(), joinPoint.getSignature());

		return joinPoint.proceed();
	}

}
