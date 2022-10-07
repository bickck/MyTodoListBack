package com.todo.list.configs.network;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

//@Component
public class MethodFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Enumeration<String> e = httpServletRequest.getHeaderNames();
		while (e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}

		if ("OPTION".equalsIgnoreCase(httpServletRequest.getMethod())
				|| "PUT".equalsIgnoreCase(httpServletRequest.getMethod())
				|| "HEAD".equalsIgnoreCase(httpServletRequest.getMethod())
				|| "TRACE".equalsIgnoreCase(httpServletRequest.getMethod())
				|| "PATCH".equalsIgnoreCase(httpServletRequest.getMethod())) {
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(httpServletRequest, httpServletResponse);
		}

	}
}
