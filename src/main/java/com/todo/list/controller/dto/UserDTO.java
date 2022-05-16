package com.todo.list.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + "]";
	}

}
