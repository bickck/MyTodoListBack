package com.todo.list.controller.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {

	private Long id;
	private String username;
	private String fileName;
	private String originName;
	private String location;
	private MultipartFile file;
	private Long fileSize;

	public FileDTO() {
		// TODO Auto-generated constructor stub
	}

	public FileDTO(String username, String fileName, String originName, String location, MultipartFile file,
			long fileSize) {
		super();
		this.username = username;
		this.fileName = fileName;
		this.originName = originName;
		this.location = location;
		this.file = file;
		this.fileSize = fileSize;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
