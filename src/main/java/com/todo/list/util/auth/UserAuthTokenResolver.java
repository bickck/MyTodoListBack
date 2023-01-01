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
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;

@Aspect
@Component
public class UserAuthTokenResolver {

	private static final String AUTHTOKENNAME = HttpHeaders.AUTHORIZATION;
	private static final String AuthAnnotationExecution = "execution(* com.todo.list.controller.*..*(.., @UserAuthToken (*), ..))";

	@Autowired
	private AuthenticationJwtProvider authenticationJwt;

	private Logger logger = LoggerFactory.getLogger(UserAuthTokenResolver.class);

	@Pointcut(AuthAnnotationExecution)
	public void userAuthPointCut() {}

	@Around(value = "userAuthPointCut()")
	public Object resolver(ProceedingJoinPoint joinPoint) throws Throwable {

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String token = httpServletRequest.getHeader(AUTHTOKENNAME);

		Object[] args = Arrays.stream(joinPoint.getArgs()).map(data -> {
			if (data instanceof UserTokenDTO) {
				data = authenticationJwt.resolveTokenToUserTokenDTO(token);
				logger.info("USER ACCESS : {}, TIME : {}, ", data.toString(), new Date().getTime());
			}
			return data;
		}).toArray();

		return joinPoint.proceed(args);
	}

}
