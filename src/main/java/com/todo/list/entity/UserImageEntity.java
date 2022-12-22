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

import com.todo.list.controller.dto.ImageDTO;

@Entity(name = "USER_IMAGE_ENTITY")
public class UserImageEntity {

	@Id
	@Column(name = "USER_IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	@Column(name = "USER_IMAGE_UUID")
	private String imageUUID;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "FILEPATH")
	private String filePath;

	@Column(name = "ORIGINALFILENAME")
	private String originalFileName;

	@Column(name = "FILESIZE")
	private Long fileSize;

	@CreationTimestamp
	@Column(name = "CREATETIMESTAMP")
	private Timestamp createTimestamp;

	@UpdateTimestamp
	@Column(name = "UPDATETIMESTAMP")
	private Timestamp updateTimestamp;

	public UserImageEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserImageEntity(UserEntity user, ImageDTO imageDTO) {
		super();
		this.user = user;
		this.fileName = imageDTO.getFileName();
		this.filePath = imageDTO.getFilePath();
		this.originalFileName = imageDTO.getOriginName();
		this.fileSize = imageDTO.getFileSize();
	}

	public UserImageEntity(UserEntity user, String fileName, String filePath, String originlFileName, Long fileSize) {
		super();
		this.user = user;
		this.fileName = fileName;
		this.filePath = filePath;
		this.originalFileName = originlFileName;
		this.fileSize = fileSize;
	}

	public UserImageEntity(UserEntity user, String uuid, String originalFileName, String filePath, String fileName) {
		super();
		this.user = user;
		this.imageUUID = uuid;
		this.originalFileName = originalFileName;
		this.filePath = filePath;
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UserImageEntity [id=" + id + ", user=" + user + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", originalFileName=" + originalFileName + ", fileSize=" + fileSize + ", createTimestamp="
				+ createTimestamp + "]";
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getImageUUID() {
		return imageUUID;
	}

	public void setImageUUID(String imageUUID) {
		this.imageUUID = imageUUID;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}
