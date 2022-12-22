package com.todo.list.repository.mapper;

import java.sql.Timestamp;

public interface TodoMapper {

	String getId();

	String getUsername();

	String getTitle();

	String getContent();

	String getIsPublish();
	
	String getUserImageUUID();

	Long getHeart();

	Long getComment();

	Timestamp getCreateTimeStamp();

	Long getPostImgCount();
	
	Long getUserImgCount();
}
