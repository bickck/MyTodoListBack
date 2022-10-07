package com.todo.list.configs.token;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AuthenticationJwt {

	private final String SECURITY_KEY = "security";
	private final String USERNAME = "username";
	private final String EMAIL = "email";
	private final String USERID = "userid";
	private long tokenValidTime = 30 * 60 * 1000L;

	public String makeToken(UserEntity userEntity) {

		Date now = new Date();

		return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setIssuer("server").setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidTime)).claim(USERID, userEntity.getId())
				.claim(USERNAME, userEntity.getUsername()).claim(EMAIL, userEntity.getEmail())
				.signWith(SignatureAlgorithm.HS256, SECURITY_KEY).compact();
	}

	public boolean tokenRefresh(String requestToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(requestToken);

			String s = Jwts.parser().setSigningKey(SECURITY_KEY).parsePlaintextJws(requestToken).getBody();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Claims getUser(String tokenString) {

		return Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(tokenString).getBody();
	}

	public UserTokenDTO getUserTokenDTO(String token) {

		int id = (int) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody().get(USERID);

		String username = (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody()
				.get(USERNAME);

		String email = (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody().get(EMAIL);

		return new UserTokenDTO(Long.valueOf(id), username, email);
	}

	public String getUserEmail(String requestToken) {

		return (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(requestToken).getBody().get(EMAIL);
	}

	public String getUserName(String requestToken) {

		return (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(requestToken).getBody().get(USERNAME);
	}

	public String getUserId(String requestToken) {

		return (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(requestToken).getBody().get(USERID);
	}

}
