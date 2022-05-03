package com.todo.list.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "USER_BACKGROUND_IMAGE")
public class UserBackGroundImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER")
	private String user;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "ORIGIN_NAME")
	private String originName;

	@Column(name = "FILESIZE")
	private Long fileSize;

	public UserBackGroundImageEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserBackGroundImageEntity(String user, String fileName, String location, String originName, Long fileSize) {
		super();
		this.user = user;
		this.fileName = fileName;
		this.location = location;
		this.originName = originName;
		this.fileSize = fileSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
