package com.todo.list.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "TODO_IMAGE_ENTITY")
public class TodoImageEntity {

	// todo id

	@Id
	@Column(name = "TODO_IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name="TO")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TODO_ID", nullable = false)
	private TodoEntity todoBoard;

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

	public TodoImageEntity() {
		// TODO Auto-generated constructor stub
	}

	public TodoImageEntity(TodoEntity todoBoard, String fileName, String originalFileName, String filePath) {
		super();
		this.todoBoard = todoBoard;
		this.fileName = fileName;
		this.originalFileName = originalFileName;
		this.filePath = filePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TodoEntity getTodoBoard() {
		return todoBoard;
	}

	public void setTodoBoard(TodoEntity todoBoard) {
		this.todoBoard = todoBoard;
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
