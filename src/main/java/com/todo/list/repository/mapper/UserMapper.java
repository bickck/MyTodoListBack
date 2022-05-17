package com.todo.list.repository.mapper;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

public interface UserMapper {

	Long getId();

	String getUsername();

	String getPassword();

	Timestamp getDate();
}
