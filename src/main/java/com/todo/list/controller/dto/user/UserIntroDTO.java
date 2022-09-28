package com.todo.list.controller.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserIntroDTO {

	// user identify number
	@NotEmpty
	private Long id;

	@NotEmpty
	private String username;

	private String introComment;

	private String fileName;

	private String location;

	public UserIntroDTO() {
		// TODO Auto-generated constructor stub
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
