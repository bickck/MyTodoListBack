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
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "USER_IMAGE_ENTITY")
public class UserImageEntity {

	@Id
	@Column(name = "USER_IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "FILEPATH")
	private String filePath;

	@Column(name = "ORIGINALFILENAME")
	private String originalFileName;

	@Column(name = "FILESIZE")
	private Long fileSize;

	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private Timestamp createDate;

	@UpdateTimestamp
	@Column(name = "UPDATEDATE")
	private Timestamp updateDate;

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

	public UserImageEntity(UserEntity user, String fileName, String filePath, String originlFileName, Long fileSize) {
		super();
		this.user = user;
		this.fileName = fileName;
		this.filePath = filePath;
		this.originalFileName = originlFileName;
		this.fileSize = fileSize;
	}

	public UserImageEntity(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "UserImageEntity [id=" + id + ", user=" + user + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", originalFileName=" + originalFileName + ", fileSize=" + fileSize + ", createDate=" + createDate
				+ "]";
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
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
