package com.todo.list.controller.dto;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class FileDTO {

	private Long id;
	private String username;
	private String fileName;
	private String originName;
	private Resource file;

	public FileDTO() {
		// TODO Auto-generated constructor stub
	}

	public FileDTO(Long id, String username, String fileName, String originName, Resource file) {
		super();
		this.id = id;
		this.username = username;
		this.fileName = fileName;
		this.originName = originName;
		this.file = file;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public Resource getFile() {
		return file;
	}

	public void setFile(Resource file) {
		this.file = file;
	}

}
