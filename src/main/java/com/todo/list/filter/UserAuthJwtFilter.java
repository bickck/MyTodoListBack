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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.redis.entity.LoginUserRedisEntity;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;

import antlr.collections.Enumerator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@WebFilter(urlPatterns = "/user/*", displayName = "Confirm User Jwt")
public class UserAuthJwtFilter implements Filter {

	@Autowired
	private AuthenticationJwtProvider authenticationJwtProvider;

	@Autowired
	private AuthRedisService authRedisService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		String accept = httpServletRequest.getHeader(HttpHeaders.ACCEPT);
		boolean isImage = accept.equals("image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
		String ip = httpServletRequest.getHeader("X-FORWARDE-FOR");

		if (ip == null) {
			ip = httpServletRequest.getRemoteAddr();
		}

		if (accessToken == null && !isImage) {
			throw new AuthenticationException("승인받지 않는 접속자");
		}

		// 토큰 검증
//		if (authenticationJwtProvider.isVertifyToken(accessToken)) {
//			throw new AuthenticationException("해당 토큰은 사용할 수 없습니다.");
//		}

		String payLoad = authenticationJwtProvider.seperatorPayLoad(accessToken);
		LoginUserRedisEntity loginUserRedisEntity = authRedisService.findTokenByPayLoad(payLoad);
		
		// redis에 있는 토큰 검증
		if (loginUserRedisEntity == null) {
			throw new JwtException("해당 토큰은 사용할 수 없습니다.");

		}

		if (!loginUserRedisEntity.getAccessToken().equals(accessToken)) {
			throw new JwtException("해당 토큰은 사용할 수 없습니다.");
		}

		// 기간이 만료되지 않는 토큰이 들어왔음에도 IP가 다른 경우
		if (loginUserRedisEntity.getAccessAddressIP() != ip) {
			// authRedisService.deleteLoginUserToken(loginUserRedisEntity.getEmail());
			// throw new AuthenticationException("해당 토큰은 사용할 수 없습니다.");
		}

		// 위의 조건들이 통과되면 해당 사용자라고 판단함 같은 Access Token이 들어왔음에도 기간이 만되었다면 내부적으로 토큰을 갱신
		if (!authenticationJwtProvider.isCheckExpiredJwt(accessToken)) {
			accessToken = authRedisService.renewAccessTokenByRefreshToken(payLoad, loginUserRedisEntity.getRefreshToken());
		}

		setResponseHeaderAccessToken(accessToken, httpServletResponse);

		chain.doFilter(request, response);
	}

	private void setResponseHeaderAccessToken(String accessToken, HttpServletResponse httpServletResponse) {

		String payLoad = authenticationJwtProvider.seperatorPayLoad(accessToken);

		LoginUserRedisEntity loginUserRedisEntity = authRedisService.findTokenByPayLoad(payLoad);

		httpServletResponse.setHeader(HttpHeaders.AUTHORIZATION, loginUserRedisEntity.getAccessToken());
	}

}
