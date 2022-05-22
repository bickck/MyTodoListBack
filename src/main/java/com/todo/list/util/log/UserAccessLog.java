package com.todo.list.util.log;

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
public class UserAccessLog {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.todo.list.controller.*..*())")
	public void accessExecution() {

	}

	@Around(value = "accessExecution()")
	public Object userAccessLog(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		//RequestContextHolder.currentRequestAttributes().

		logger.info("Access address = {}", httpServletRequest.getAsyncContext());
		logger.info("Access Method = {} , {}", joinPoint.getThis(), joinPoint.getSignature());

		return joinPoint.proceed();
	}

}
