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

	/**
	 * 
	 * @param userImageEntity
	 * @return
	 */

	@Transactional(rollbackFor = Exception.class)
	public UserImageEntity userImageSave(UserImageEntity userImageEntity) {
		return imageRepository.save(userImageEntity);
	}

	/**
	 * 
	 * @param id
	 */

	@Transactional(rollbackFor = Exception.class)
	public void deleteUserIntroImage(Long id) {
		UserImageEntity userIntroImage = imageRepository.getById(id);
		imageRepository.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	@Transactional(readOnly = true)
	public UserImageEntity findUserImageByUserId(Long id) {
		return imageRepository.findUserIntroImageByUserId(id);
	}

	/**
	 * 
	 * @param user
	 * @param fileName
	 * @param filePath
	 */

	@Transactional(rollbackFor = Exception.class)
	public void updateDefaultUserIntroImage(UserImageEntity userIntroImage, String originalFileName, String filePath) {

		userIntroImage.setFileName("DEFAULT");

		userIntroImage.setFilePath(filePath);

		userIntroImage.setFileSize((long) 0);

		userIntroImage.setOriginalFileName(originalFileName);

		imageRepository.save(userIntroImage);

	}
}
