package com.todo.list.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.todo.list.controller.dto.ImageInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "ADMIN_IMAGE")
public class AdminImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "ORIGINALFILENAME")
	private String originalFileName;

	@NotNull
	@Column(name = "FILEPATH")
	private String filePath;

	@NotNull
	private Long fileSize;

	public AdminImageEntity() {
		// TODO Auto-generated constructor stub
	}

	public AdminImageEntity(@NotNull String originalFileName, @NotNull String filePath) {
		this.originalFileName = originalFileName;
		this.filePath = filePath;

	}

	public AdminImageEntity(Long id, @NotNull String originalFileName, @NotNull String filePath,
			@NotNull Long fileSize) {
		super();
		this.id = id;
		this.originalFileName = originalFileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
