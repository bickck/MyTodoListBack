package com.todo.list.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="USER_BACKGROUND_IMAGE")
public class UserBackGroundImageEntity {

	@Id
	private Long id;
	
	@Column(name="user")
	private String user;
	
	
	
	
}
