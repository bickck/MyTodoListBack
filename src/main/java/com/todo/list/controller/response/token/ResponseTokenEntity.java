package com.todo.list.controller.response.token;

import org.springframework.http.HttpStatus;

import com.todo.list.controller.response.ResponseCode;

public class ResponseTokenEntity extends ResponseCode {

	private String accessToken;

	private String refreshToken;

	public ResponseTokenEntity() {
		// TODO Auto-generated constructor stub
	}

	public ResponseTokenEntity(String accessToken, HttpStatus code) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = "";
		super.setCode(code);
	}

	public ResponseTokenEntity(String accessToken, String refreshToken, HttpStatus code) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		super.setCode(code);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
