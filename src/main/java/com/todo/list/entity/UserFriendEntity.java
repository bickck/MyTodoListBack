package com.todo.list.entity;

import java.sql.Timestamp;

public class UserFriendEntity {

	private Long id;
	private UserEntity follower;
	private Timestamp applicationDate;

	public UserFriendEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserFriendEntity(Long id, UserEntity follower, Timestamp applicationDate) {
		super();
		this.id = id;
		this.follower = follower;
		this.applicationDate = applicationDate;
	}

}
