package com.todo.list.util.auth.provider;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AuthenticationJwtProvider {

	private final String SECURITY_KEY = "security";
	private final String USERNAME = "username";
	private final String EMAIL = "email";
	private final String USERID = "userid";
	private long accessTokenValidTime = 60 * 60 * 1000L;
	private long refreshTokeValidTime = 60 * 60 * 100000000L;//

	public String createToken(UserEntity userEntity, Long expireTime) {

		Date now = new Date();
		Date expirationTime = new Date(now.getTime() + expireTime);

		return Jwts.builder().
				setHeaderParam(Header.TYPE, Header.JWT_TYPE).setIssuer("server").setIssuedAt(now)
				.setExpiration(expirationTime).setSubject(userEntity.getEmail())
				.claim(USERID, userEntity.getId())
				.claim(USERNAME, userEntity.getUsername())
				.signWith(SignatureAlgorithm.HS256, SECURITY_KEY).compact();
	}

	public String createAccessToken(UserEntity userEntity) {

		return createToken(userEntity, this.accessTokenValidTime);
	}

	public String createRefreshToken(UserEntity userEntity) {

		return createToken(userEntity, this.refreshTokeValidTime);
	}

	public boolean isVertifyToken(String claimsJws) {

		try {
			Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(claimsJws);

		} catch (MissingClaimException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;

		} catch (IncorrectClaimException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean isCheckExpiredJwt(String jwt) {

		try {
			Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(jwt);

		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public UserTokenDTO resolveTokenToUserTokenDTO(String token) {

		int id = 0;
		String username = null;
		String email = null;

		try {

			id = (int) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody().get(USERID);

			username = (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody().get(USERNAME);

			email = (String) Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody().getSubject();

		} catch (ExpiredJwtException e) {
			// TODO: handle exception

			e.printStackTrace();

			return null;
		}

		return new UserTokenDTO(Long.valueOf(id), username, email);
	}

	public String seperatorPayLoad(String token) {

		if (token.equals(null)) {
			throw new NullPointerException();
		}

		return token.split("\\.")[1];
	}
}
