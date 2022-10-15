package com.todo.list.redis;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.controller.dto.auth.UserTokenDTO;

@Aspect
@Component
public class RedisCacheableAop {

//	@Autowired
//	private CrudRepository<?, Long> crudRepository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.todo.list.*..*(.., @RedisCacheable (*), ..))")
	public void cacheablePointCut() {

	}

	@Around(value = "cacheablePointCut()")
	public Object executeCacheableTargetRepository(ProceedingJoinPoint joinPoint) throws Throwable {

		MethodSignature targetMethodInfo = (MethodSignature) joinPoint.getSignature();
		RedisCacheable redisCacheable = targetMethodInfo.getMethod().getAnnotation(RedisCacheable.class); // RedisCacheable 어노테이션이 달린 메서드에서의 위치를 리턴
		
		logger.info("execute custom annotation proceeding at method => {}",targetMethodInfo.getMethod());
		logger.info("execute custom annotation proceeding parameter => {}",redisCacheable.value());

		
		return joinPoint.proceed();
	}

}
