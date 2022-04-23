package com.todo.list.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/user/*")
public class UserApiFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		System.out.println("UserApiFilter in");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String authorization = httpServletRequest.getHeader("authorization");

		if (authorization == null) {
			System.out.println("authorizaion is null");
			throw new IllegalAccessError();
		} else {
			System.out.println(authorization);
			chain.doFilter(request, response);
		}
		System.out.println("UserApiFilter out~");

	}

}
