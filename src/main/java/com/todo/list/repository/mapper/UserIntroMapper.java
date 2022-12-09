package com.todo.list.repository.mapper;

import com.todo.list.entity.UserImageEntity;

public interface UserIntroMapper {

	Long getId();

	String getUsername();

	String getIntroComment();
	
	String getOriginalFileName();
	
	String getFileName();
	
	String getFilePath();
}
