package com.todo.list.redis.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "UserMessageChannel", timeToLive = 360000)
public class MessageChannelEntity {

	@Id
	private Long userid;

	private String userMessageChannel;

	private String userConnectSession;

	public MessageChannelEntity(Long userid, String userMessageChannel, String userConnectSession) {
		super();
		this.userid = userid;
		this.userMessageChannel = userMessageChannel;
		this.userConnectSession = userConnectSession;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserMessageChannel() {
		return userMessageChannel;
	}

	public void setUserMessageChannel(String userMessageChannel) {
		this.userMessageChannel = userMessageChannel;
	}

	public String getUserConnectSession() {
		return userConnectSession;
	}

	public void setUserConnectSession(String userConnectSession) {
		this.userConnectSession = userConnectSession;
	}

}
