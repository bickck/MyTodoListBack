package com.todo.list.controller.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
	
	private Long id;

	@NotEmpty
	private String username;

	private String password;

	private String introComment;

	private String userImageName;

	private String userIagePath;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserDTO(@NotEmpty String username, String password, String introComment, String userImageName,
			String userIagePath) {
		super();
		this.username = username;
		this.password = password;
		this.introComment = introComment;
		this.userImageName = userImageName;
		this.userIagePath = userIagePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIntroComment() {
		return introComment;
	}

	public void setIntroComment(String introComment) {
		this.introComment = introComment;
	}

	public String getUserImageName() {
		return userImageName;
	}

	public void setUserImageName(String userImageName) {
		this.userImageName = userImageName;
	}

	public String getUserIagePath() {
		return userIagePath;
	}

	public void setUserIagePath(String userIagePath) {
		this.userIagePath = userIagePath;
	}

}
