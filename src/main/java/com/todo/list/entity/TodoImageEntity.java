package com.todo.list.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TodoImageEntity {

	// todo id

	@Id @Column(name="TODO_IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "TODO_ID", nullable = false)
	private TodoEntity todoEntity;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name="ORIGINALFILENAME")
	private String originalFileName;
	
	@Column(name = "FILEPATH")
	private String filePath;

}
