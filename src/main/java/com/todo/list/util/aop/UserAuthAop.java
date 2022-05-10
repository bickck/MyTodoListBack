package com.todo.list.util.aop;

import java.net.http.HttpHeaders;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletContextResourceLoader;

import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.util.UserUtil;

@Aspect
@Component
public class UserAuthAop {

	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

	Logger logger = LoggerFactory.getLogger(UserAuthAop.class);

	@Pointcut("execution(* com.todo.list.controller.UserController..*(.., @UserAuthToken (*), ..))")
	public void cut() {
	}

	@Around(value = "cut()")
	public Object test(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String token = httpServletRequest.getHeader("authorization");
		authenticationJwtToken.getUserTokenDTO(token);

		Object[] args = Arrays.stream(joinPoint.getArgs()).map(data -> {
			if (data instanceof UserTokenDTO) {
				data = authenticationJwtToken.getUserTokenDTO(token);
			}
			return data;
		}).toArray();

		return joinPoint.proceed(args);
	}

}
