package com.todo.list.controller.dto.user;

import javax.validation.constraints.NotNull;

import com.todo.list.entity.UserEntity;

public class LoginUserDTO {

	private Long id;
	private String username;

	private String introComment;
	private String userImagePath;

	public LoginUserDTO(@NotNull UserEntity userEntity) {
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
	}

	public LoginUserDTO(@NotNull Long id, @NotNull String username) {
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

	public String getIntroComment() {
		return introComment;
	}

	public void setIntroComment(String introComment) {
		this.introComment = introComment;
	}

	public String getUserImagePath() {
		return userImagePath;
	}

	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}

}
