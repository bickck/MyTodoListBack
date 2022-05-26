package com.todo.list.controller.dto.service;

import org.springframework.core.io.Resource;

public class BackGroundDTO {

	private Long id;

	private String filename;

	public BackGroundDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public BackGroundDTO(Long id, String filename) {
		super();
		this.id = id;
		this.filename = filename;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
}
