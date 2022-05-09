package com.todo.list.controller.builder;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.todo.list.entity.UserEntity;

public class UserQuoteEntityBuilder {
	
	private Long id;	
	private UserEntity user;
	private String queto;
	private String author;
	private Timestamp createDate;
	
}
