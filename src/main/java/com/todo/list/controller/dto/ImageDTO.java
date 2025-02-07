package com.todo.list.controller.dto;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {

	private Long id;
	private String username;
	private String fileName;
	private String filePath;
	private String originName;
	private MultipartFile multipartFile;
	private Long fileSize;

	public ImageDTO() {
		// TODO Auto-generated constructor stub
	}

	public ImageDTO(String fileName, String originName, String filePath, Long fileSize) {
		this.fileName = fileName;
		this.originName = originName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public ImageDTO(Long id, String username, String fileName, String originName, MultipartFile multipartFile,
			Long fileSize) {
		super();
		this.id = id;
		this.username = username;
		this.fileName = fileName;
		this.originName = originName;
		this.multipartFile = multipartFile;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
