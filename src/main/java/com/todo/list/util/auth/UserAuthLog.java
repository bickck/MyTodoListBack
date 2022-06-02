package com.todo.list.util.auth;

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

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.util.UserUtil;

@Aspect
@Component
public class UserAuthLog {

	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

	private Logger logger = LoggerFactory.getLogger(UserAuthLog.class);

	@Pointcut("execution(* com.todo.list.controller.UserController..*(.., @UserAuthToken (*), ..))")
	public void cut() {
	}

	@Around(value = "cut()")
	public Object userAuthAop(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String token = httpServletRequest.getHeader("authorization");

		Object[] args = Arrays.stream(joinPoint.getArgs()).map(data -> {
			if (data instanceof UserTokenDTO) {
				data = authenticationJwtToken.getUserTokenDTO(token);
				logger.info("USER ACCESS : {}, TIME : {}, ", data.toString());
			}
			return data;
		}).toArray();

		return joinPoint.proceed(args);
	}

}
