package com.todo.list.service.image;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.FileDTO;
import com.todo.list.domain.UserBackGroundImageEntity;
import com.todo.list.domain.UserEntity;
import com.todo.list.repository.UserImageRepository;
import com.todo.list.repository.UserRepository;

@Service
public class UserBackGroundImgService {

	private UserImageRepository imageRepository;
	private UserRepository userRepository;

	@Autowired
	public UserBackGroundImgService(UserImageRepository imageRepository, UserRepository userRepository) {
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void userImageSave(UserBackGroundImageEntity backGroundImageEntity) {

		imageRepository.save(backGroundImageEntity);
	}

	public void userImgDelete() {

	}
}
