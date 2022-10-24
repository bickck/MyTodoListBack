package com.todo.list.repository.mapper;

import java.sql.Timestamp;

public interface QuoteMapper {

	Long getId();

	String getQuote();

	String getAuthor();

	Timestamp getCreateTimestamp();

	String getUsername();
	
	Long getHeart();

}
