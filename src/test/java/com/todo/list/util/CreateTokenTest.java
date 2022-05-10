package com.todo.list.util;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.todo.list.controller.dto.UserTokenDTO;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CreateTokenTest {

	private final String SECURITY_KEY = "security";
	private final String USERNAME = "username";
	private final String USERID = "userid";
	private long tokenValidTime = 30 * 60 * 1000L;

	@Test
	public void createTokenTest(UserTokenDTO entity) {
		Date now = new Date();

//		Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setIssuer("server").setIssuedAt(now)
//				.setExpiration(new Date(now.getTime() + tokenValidTime)).claim(USERID, entity.getId())
//				.claim(USERNAME, entity.getUsername()).signWith(SignatureAlgorithm.HS256, SECURITY_KEY).compact();
	}
}
