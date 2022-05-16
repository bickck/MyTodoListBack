package com.todo.list.controller.dto;

public class UserTokenDTO {

	private Long id;
	private String username;

	public UserTokenDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserTokenDTO(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserTokenDTO [id=" + id + ", username=" + username + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void Long(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
