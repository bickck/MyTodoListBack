package com.todo.list.controller.builder;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.FileDTO;

public class BackGroundImgBuilder {
	private Long id;
	private String username;
	private String fileName;
	private String originName;
	private MultipartFile multipartFile;
	private Long fileSize;
	
	public FileDTO builder() {
		return new FileDTO(id, username, fileName, originName, multipartFile, fileSize);
	}

	public BackGroundImgBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public BackGroundImgBuilder setUserName(String username) {
		this.username = username;
		return this;
	}

	public BackGroundImgBuilder setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public BackGroundImgBuilder setOriginName(String originName) {
		this.originName = originName;
		return this;
	}

	public BackGroundImgBuilder setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
		return this;
	}

	public BackGroundImgBuilder setFileSize(Long fileSize) {
		this.fileSize = fileSize;
		return this;
	}

}
