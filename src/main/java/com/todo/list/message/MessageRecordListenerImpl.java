package com.todo.list.message;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.service.EventMessageService;
import com.todo.list.util.auth.UserAuthTokenResolver;

import antlr.debug.Event;

@Aspect
@Component
public class MessageRecordListenerImpl {

	private Logger logger = LoggerFactory.getLogger(MessageRecordListenerImpl.class);

	@Autowired
	private EventMessageService messageService;

	@Around(value = "@annotation(com.todo.list.message.EventMessage)")
	public Object messageRecorder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		EventMessage eventMessage = methodSignature.getMethod().getAnnotation(EventMessage.class);

		Object[] args = proceedingJoinPoint.getArgs();
		UserTokenDTO userTokenDTO = null;

		for (Object arg : args) {			
			if(arg != null) {
				if (arg.getClass().equals(UserTokenDTO.class)) {
					userTokenDTO = (UserTokenDTO) arg;
				}
			}
		}
		
		// 쓰레드를 사용한다면 이곳 부터 사용
		Object object = proceedingJoinPoint.proceed();
		messageService.saveMessage(eventMessage.message(), userTokenDTO.getId());

		return object;
	}
}
