package com.todo.list.controller.dto.auth;

public class UserTokenDTO {

	private Long id;
	private String username;
	private String email;

	public UserTokenDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserTokenDTO(Long id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserTokenDTO [id=" + id + ", username=" + username + ", email=" + email + "]";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
