package com.todo.list.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "USER")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CREATEDATE")
	@CreationTimestamp
	private Timestamp date;

	@OneToMany(mappedBy = "username")
	private List<UserQuoteEntity> quotes;

	@OneToMany(mappedBy = "user")
	private List<UserTodoEntity> todos;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserEntity(@NotNull Long id, @NotNull String username, @NotNull String password, @NotNull Timestamp date) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
