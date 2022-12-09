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

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import antlr.collections.Enumerator;
import io.jsonwebtoken.Header;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/user/*", displayName = "Confirm User Jwt")
public class UserAuthJwtFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		
		String accept = httpServletRequest.getHeader(HttpHeaders.ACCEPT);
		
		boolean isImage = accept.equals("image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
				
		System.out.println(accept);
		System.out.println(isImage);

		if (authorization == null && !isImage) {
			throw new AuthenticationException("승인받지 않는 접속자");
		} else {
			chain.doFilter(request, response);
		}
	}

}
