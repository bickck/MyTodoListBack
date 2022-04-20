package com.todo.list.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.domain.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtLoginToken {

	private final String securityKey = "security";
	private long tokenValidTime = 30 * 60 * 1000L;

	public String makeToken(UserEntity userEntity) {

		Date now = new Date();

		return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setIssuer("server").setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidTime)).claim("username", userEntity.getUsername())
				.claim("password", userEntity.getPassword()).signWith(SignatureAlgorithm.HS256, securityKey).compact();
	}

	public boolean validCheck(String tokenString) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(tokenString);

			String s = Jwts.parser().setSigningKey(securityKey).parsePlaintextJws(tokenString).getBody();
			System.out.println(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Claims getUser(String tokenString) {

		return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(tokenString).getBody();
	}

	public String getUsername(String tokenString) {

		return (String) Jwts.parser().setSigningKey(securityKey).parseClaimsJws(tokenString).getBody().get("username");
	}

	public String getPassword(String tokenString) {

		return (String) Jwts.parser().setSigningKey(securityKey).parseClaimsJws(tokenString).getBody().get("password");
	}
}
