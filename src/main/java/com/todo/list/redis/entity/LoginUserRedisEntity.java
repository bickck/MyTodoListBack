package com.todo.list.redis.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.todo.list.entity.TodoEntity;

@RedisHash(value = "AccessAuthority", timeToLive = 360000)
public class LoginUserRedisEntity {

	@Id
	private String id;

	private String accessToken;

	private String refreshToken;

	private String accessAddressIP;

//	private Timestamp createTime;

	public LoginUserRedisEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAccessAddressIP() {
		return accessAddressIP;
	}

	public void setAccessAddressIP(String accessAddressIP) {
		this.accessAddressIP = accessAddressIP;
	}

//	public Timestamp getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Timestamp createTime) {
//		this.createTime = createTime;
//	}

}
