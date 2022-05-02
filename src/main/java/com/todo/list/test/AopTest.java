package com.todo.list.test;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.security.AuthenticationJwtToken;

@Aspect
@Component
public class AopTest {

	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

//	@Pointcut("execution(* com.todo.list.test.TestController..*(.., @TokenValidator (*), ..))")
//	public void cut2() {
//	}
//
//	@Around(value = "cut2()")
//	public Object test2(ProceedingJoinPoint joinPoint) throws Throwable {
////		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//
//		String token = httpServletRequest.getHeader("authorization");
//		authenticationJwtToken.getUserTokenDTO(token);
//
//		Object[] args = Arrays.stream(joinPoint.getArgs()).map(data -> {
//			if (data instanceof UserTokenDTO) {
//				data = authenticationJwtToken.getUserTokenDTO(token);
//			}
//			return data;
//		}).toArray();
//
//		return joinPoint.proceed(args);
//	}

//	@Before(value = "")
//	public Object annotationTest(ProceedingJoinPoint joinPoint) throws Throwable {
//		return joinPoint;
//		
//	}
}
