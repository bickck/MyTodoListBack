package com.todo.list.service.image.user;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.service.FileDTO;
import com.todo.list.entity.BackGroundImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.UserImageRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.service.image.ImageService;
import com.todo.list.service.image.UserImageUploadService;

@Service
public class UserBackGroundImgService {

	private UserImageRepository imageRepository;
	private UserRepository userRepository;
	private ImageService imageService = new UserImageUploadService();

	@Autowired
	public UserBackGroundImgService(UserImageRepository imageRepository, UserRepository userRepository) {
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void userImageSave(BackGroundImageEntity backGroundImageEntity) {
		imageRepository.save(backGroundImageEntity);
	}

	public BackGroundImageEntity userImgDelete(Long id) {
		BackGroundImageEntity backGroundImageEntity = imageRepository.getById(id);
		imageRepository.deleteById(id);
		return backGroundImageEntity;
	}

	public BackGroundImageEntity findById(long id) {
		return imageRepository.findById(id).get();
	}
}
