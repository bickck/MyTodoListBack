package com.todo.list.controller.dto.response;

import java.sql.Timestamp;

public interface PostInterface {

	String getId();

	String getUsername();

	String getTitle();

	String getContent();

	String getIsPublish();

	Long getHeart();

	Long getComment();

	Timestamp getCreateTimeStamp();

	Long getPostImgCount();
	
	Long getUserImgCount();
}
