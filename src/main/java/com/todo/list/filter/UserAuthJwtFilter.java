package com.todo.list.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.security.sasl.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import antlr.collections.Enumerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/user/*", displayName = "Confirm User Jwt")
public class UserAuthJwtFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Come in UserAuthJwtFilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String authorization = httpServletRequest.getHeader("Authorization");

		if (authorization == null) {
			throw new AuthenticationException("승인받지 않는 접속자");
		} else {
			chain.doFilter(request, response);
		}
	}

}
