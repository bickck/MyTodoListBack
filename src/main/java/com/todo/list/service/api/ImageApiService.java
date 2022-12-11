package com.todo.list.service.api;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.repository.image.TodoImageRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.repository.mapper.ImageMapper;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.upload.TodoImageUploadService;

@Service
public class ImageApiService {

	private TodoImageRepository todoImageRepository;
	private UserImageRepository userImageRepository;
	private ImageUploadService imageUploadService;

	@Autowired
	public ImageApiService(TodoImageRepository todoImageRepository, UserImageRepository userImageRepository) {
		this.todoImageRepository = todoImageRepository;
		this.userImageRepository = userImageRepository;
		this.imageUploadService = new TodoImageUploadService();
	}

	/**
	 * Todo 이미지 가져오는 메소드
	 * 
	 * @param TodoId
	 * @return
	 */

	@Transactional
	public List<ImageMapper> findTodoImageByTodoId(Long id) {

		return todoImageRepository.findTodoImageById(id);
	}

	/**
	 * User 이미지 가져오는 메소드
	 * 
	 * @param UserId
	 * @return
	 */

	@Transactional
	public ImageMapper findUserImageByUserId(Long id) {

		return userImageRepository.findUserIntroImageById(id);
	}

	/**
	 * 
	 * @param filePath
	 * @param originalName
	 * @return
	 */

	public Resource todoRealImageResource(String filePath, String originalName) {

		String storageFilePath = todoImageRepository.findFilePathByOriginalFileNameAndFileName(originalName, filePath);

		if (storageFilePath == null) {
			return null;
		}

		return imageUploadService.findImageInDirectory(originalName, storageFilePath);
	}

	/**
	 * 
	 * @param filePath
	 * @param originalName
	 * @return
	 */

	public Resource userRealImageResource(String filePath, String originalName) {

		String storageFilePath = userImageRepository.findFilePathByOriginalFileNameAndFileName(originalName, filePath);

		return imageUploadService.findImageInDirectory(originalName, storageFilePath);
	}

}
