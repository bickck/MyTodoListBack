package com.todo.list.util.aop;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.dto.UserTokenDTO;

@Aspect
@Component
public class UserAuthDelay {

	Logger logger = LoggerFactory.getLogger(UserAuthAop.class);

	@Pointcut("execution(* com.todo.list.controller.MainController..main(..))")
	public void cut() {
	}

	@Around(value = "cut()")
	public Object test(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("delay");
		TimeUnit.SECONDS.sleep(2);

		return joinPoint.proceed();
	}
}
