package com.todo.list.security;

import java.util.Date;

import com.todo.list.controller.dto.UserDTO;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginResponseToken {
	
	
	public String token(UserDTO userDTO) {
		return Jwts.builder()
				.setHeaderParam(Header.TYPE,Header.JWT_TYPE)
				.setIssuer("server")
				.setIssuedAt(new Date())
				.setExpiration(new Date())
				.claim("username", userDTO.getUsername())
				.claim("password", userDTO.getPassword())
				.signWith(SignatureAlgorithm.HS256, "security")
				.compact();
	}

}
