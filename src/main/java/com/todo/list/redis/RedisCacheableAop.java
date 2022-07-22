package com.todo.list.redis;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.controller.dto.auth.UserTokenDTO;

@Aspect
@Component
public class RedisCacheableAop {
	
	

	@Pointcut("execution(* com.todo.list.*..*(.., @RedisCacheable (*), ..))")
	public void cacheablePointCut() {
		
	}

	@Around(value = "cacheablePointCut()")
	public Object userAuthAop(ProceedingJoinPoint joinPoint) throws Throwable {

		
		

		
		return joinPoint.proceed();
	}

}
