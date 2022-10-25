package com.todo.list.repository.mapper;

import java.sql.Timestamp;

public interface TodoCommentMapper {

	Long getId();

	String getUsername();

	String getComment();

	Timestamp getCreateTimeStamp();
}
