package com.todo.list.util.auth;

import java.util.Arrays;
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

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.dto.auth.UserTokenDTO;

@Aspect
@Component
public class UserAuthLog {

	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

	private Logger logger = LoggerFactory.getLogger(UserAuthLog.class);

	@Pointcut("execution(* com.todo.list.controller.UserController..*(.., @UserAuthToken (*), ..))")
	public void authLogPointCut() {
	}

	@Around(value = "authLogPointCut()")
	public Object userAuthLogEvent(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String token = httpServletRequest.getHeader("authorization");

		Object[] args = Arrays.stream(joinPoint.getArgs()).map(data -> {
			if (data instanceof UserTokenDTO) {
				data = authenticationJwtToken.getUserTokenDTO(token);
				logger.info("USER ACCESS : {}, TIME : {}, ", data.toString(), new Date().getTime());
			}
			return data;
		}).toArray();

		return joinPoint.proceed(args);
	}

}
