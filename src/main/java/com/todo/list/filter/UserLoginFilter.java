package com.todo.list.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.security.sasl.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.list.controller.dto.user.LoginUserDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.redis.entity.LoginUserRedisEntity;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;

@WebFilter(urlPatterns = "/auth/*", displayName = "Check User Jwt")
public class UserLoginFilter implements Filter {

	@Autowired
	private AuthenticationJwtProvider authenticationJwt;

	@Autowired
	private AuthRedisService authRedisService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		String ip = httpServletRequest.getHeader("X-FORWARDE-FOR");

		if (ip == null) {
			ip = httpServletRequest.getRemoteAddr();
		}

		if (authorization != null) {
			String payLoad = authenticationJwt.seperatorPayLoad(authorization);
			LoginUserRedisEntity loginUserRedisEntity = authRedisService.findTokenByPayLoad(payLoad);

			if (loginUserRedisEntity != null) {
				authRedisService.deleteLoginUserToken(payLoad);
			}
			setResponseAccessToken(httpServletResponse);
		}

		chain.doFilter(request, response);
		// 유저가 로그인한 흔적이 있는지 확인
		// 있다면 이전 로그인했던 토큰 모두 삭제

//
//		if (authorization == null) {
//
//			chain.doFilter(request, response);
//		}

	}

	private void setResponseAccessToken(HttpServletResponse httpServletResponse) {

		String accessToken = "1234";

		httpServletResponse.addCookie(new Cookie("jwt", accessToken));
		httpServletResponse.addHeader("jwt", accessToken);
	}
//
//	private UserDTO convertRequestData(HttpServletRequest httpServletRequest)
//			throws JsonMappingException, JsonProcessingException {
//		ServletInputStream inputStream = null;
//		String messageBody = null;
//
//		try {
//			inputStream = httpServletRequest.getInputStream();
//			messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return new ObjectMapper().readValue(messageBody, UserDTO.class);
//	}
}
