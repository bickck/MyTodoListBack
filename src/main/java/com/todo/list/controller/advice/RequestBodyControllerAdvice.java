package com.todo.list.controller.advice;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.main.QuoteController;

//@RestControllerAdvice(basePackageClasses = QuoteController.class)
public class RequestBodyControllerAdvice implements RequestBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return methodParameter.getContainingClass() == QuoteController.class && targetType.getTypeName().equals(QuoteDTO.class.getTypeName());
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		// TODO Auto-generated method stub
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return null;
	}

}
