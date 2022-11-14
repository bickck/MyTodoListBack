package com.todo.list.service.image.user;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.upload.UserImageUploadService;

@Service
public class UserImageService {

	private UserImageRepository imageRepository;
	private UserRepository userRepository;

	@Autowired
	public UserImageService(UserImageRepository imageRepository, UserRepository userRepository) {
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void userImageSave(UserImageEntity userImageEntity) {
		imageRepository.save(userImageEntity);
	}

	public UserImageEntity userImgDelete(Long id) {
		UserImageEntity backGroundImageEntity = imageRepository.getById(id);
		imageRepository.deleteById(id);
		return backGroundImageEntity;
	}

	public UserImageEntity findById(long id) {
		return imageRepository.findById(id).get();
	}
}
