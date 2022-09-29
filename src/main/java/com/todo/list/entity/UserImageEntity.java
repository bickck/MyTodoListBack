package com.todo.list.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "USER_IMAGE")
public class UserImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "USER_ID")
//	private UserEntity user;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "ORIGIN_NAME")
	private String originName;

	@Column(name = "FILESIZE")
	private Long fileSize;

	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	public UserImageEntity() {
		// TODO Auto-generated constructor stub
	}

//	public UserImageEntity(UserImageEntity userImageEntity) {
//		super();
//		this.user = userImageEntity.getUser();
//		this.fileName = userImageEntity.getFileName();
//		this.location = userImageEntity.getLocation();
//		this.originName = userImageEntity.getOriginName();
//		this.fileSize = userImageEntity.getFileSize();
//	}

	public UserImageEntity(UserEntity user, String fileName, String location, String originName, Long fileSize) {
		super();
		this.user = user;
		this.fileName = fileName;
		this.location = location;
		this.originName = originName;
		this.fileSize = fileSize;
	}

	public UserImageEntity(String fileName, String location) {
		super();
		this.fileName = fileName;
		this.location = location;
	}

	@Override
	public String toString() {
		return "UserImageEntity [id=" + id + ", user=" + user + ", fileName=" + fileName + ", location=" + location
				+ ", originName=" + originName + ", fileSize=" + fileSize + ", createDate=" + createDate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
