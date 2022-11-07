package com.todo.list.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.todo.list.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class TodoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String user;

	@Size(max = 1000, message = "Check max size")
	@NotEmpty(message = "Check your title")
	private String title;

	@NotEmpty(message = "Check your content")
	private String content;

	@NotEmpty(message = "Check is published")
	private String isPublish;

	private Long heart;

	private Long comment;

	private String originalFileName;

	private Timestamp createTimeStamp;

	public TodoDTO() {
		// TODO Auto-generated constructor stub
	}

	public TodoDTO(Long id, String user, String title, String content, Timestamp createTimeStamp) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.createTimeStamp = createTimeStamp;
	}

	public TodoDTO(String user, String title, String content, String isPublish, Long heart, Long comment) {
		super();
		this.user = user;
		this.title = title;
		this.content = content;
		this.isPublish = isPublish;
		this.heart = heart;
		this.comment = comment;
	}

	public TodoDTO(Long id, String user, String title, String content, String isPublish, Timestamp createTimeStamp) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.isPublish = isPublish;
		this.createTimeStamp = createTimeStamp;
	}

	@Override
	public String toString() {
		return "TodoDTO [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + ", isPublish="
				+ isPublish + ", heart=" + heart + ", comment=" + comment + ", createTimeStamp=" + createTimeStamp
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public Timestamp getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Timestamp createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public Long getHeart() {
		return heart;
	}

	public void setHeart(Long heart) {
		this.heart = heart;
	}

	public Long getComment() {
		return comment;
	}

	public void setComment(Long comment) {
		this.comment = comment;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

}
