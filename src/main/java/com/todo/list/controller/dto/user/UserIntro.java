package com.todo.list.controller.dto.user;

import com.todo.list.entity.UserImageEntity;

public interface UserIntro {

	Long getId();

	String getUsername();

	String getIntroComment();

	UserImageInfo getUserImageEntity();

	interface UserImageInfo {
		String fileName();
		String location();
	}
}
