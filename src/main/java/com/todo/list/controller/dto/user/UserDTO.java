package com.todo.list.controller.dto.user;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.todo.list.util.validation.annotation.Password;
import com.todo.list.util.validation.group.Comment;
import com.todo.list.util.validation.group.LoginAccessArgumentGroup;
import com.todo.list.util.validation.group.RegisterAccessArgumentGroup;

public class UserDTO {

	private Long id;

	@Email(groups = { RegisterAccessArgumentGroup.class, LoginAccessArgumentGroup.class })
	private String email;

	@NotNull(groups = { RegisterAccessArgumentGroup.class })
	private String username;

	@Password(groups = { RegisterAccessArgumentGroup.class, LoginAccessArgumentGroup.class })
	private String password;

	@Size(max = 255, groups = Comment.class)
	@NotNull(groups = Comment.class)
	@NotBlank(groups = Comment.class)
	@NotEmpty(groups = Comment.class)
	private String introComment;

	private String nickName;

	private Timestamp birth;

	private String userImageName;

	private String userIagePath;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(@NotEmpty String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UserDTO(Long id, @NotEmpty String email, String username, String password, String introComment,
			String userImageName, String userIagePath) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.introComment = introComment;
		this.userImageName = userImageName;
		this.userIagePath = userIagePath;
	}

	public UserDTO(@NotEmpty String username, String password, String introComment, String userImageName,
			String userIagePath) {
		super();
		this.username = username;
		this.password = password;
		this.introComment = introComment;
		this.userImageName = userImageName;
		this.userIagePath = userIagePath;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", introComment=" + introComment + ", userImageName=" + userImageName + ", userIagePath="
				+ userIagePath + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Timestamp getBirth() {
		return birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	public String getIntroComment() {
		return introComment;
	}

	public void setIntroComment(String introComment) {
		this.introComment = introComment;
	}

	public String getUserImageName() {
		return userImageName;
	}

	public void setUserImageName(String userImageName) {
		this.userImageName = userImageName;
	}

	public String getUserIagePath() {
		return userIagePath;
	}

	public void setUserIagePath(String userIagePath) {
		this.userIagePath = userIagePath;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
