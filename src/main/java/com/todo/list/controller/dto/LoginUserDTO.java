package com.todo.list.controller.dto;

import com.todo.list.domain.UserEntity;

public class LoginUserDTO {

	private Long id;
	private String username;

	public LoginUserDTO(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
	}

	public LoginUserDTO(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
