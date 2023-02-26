package com.todo.list.service.api;

import java.util.List;

import com.todo.list.service.image.physical.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.repository.image.TodoImageRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.repository.mapper.ImageMapper;

@Service
public class ImageApiService {

	private TodoImageRepository todoImageRepository;
	private UserImageRepository userImageRepository;

	private ImageUploader imageUploader;


	@Autowired
	public ImageApiService(TodoImageRepository todoImageRepository, UserImageRepository userImageRepository,ImageUploader imageUploader) {
		this.todoImageRepository = todoImageRepository;
		this.userImageRepository = userImageRepository;
		this.imageUploader = imageUploader;
	}

	/**
	 * Todo 이미지 가져오는 메소드
	 * 
	 * @param id
	 * @return
	 */

	@Transactional
	public List<ImageMapper> findTodoImageByTodoId(Long id) {

		return todoImageRepository.findTodoImageById(id);
	}

	/**
	 * User 이미지 가져오는 메소드
	 * 
	 * @param id
	 * @return
	 */

	@Transactional
	public ImageMapper findUserImageByUserId(Long id) {

		return userImageRepository.findUserIntroImageById(id);
	}

	/**
	 * User 이미지 가져오는 메소드
	 * 
	 * @param uuid
	 * @return
	 */

	@Transactional
	public ImageMapper findUserImageByImageUUID(String uuid) {

		return userImageRepository.findUserImageByImageUUID(uuid);
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

		return imageUploader.findImageInDirectory(originalName, storageFilePath);
	}

	/**
	 * 
	 * @param filePath
	 * @param originalName
	 * @return
	 */

	public Resource userRealImageResource(String filePath, String originalName) {

		String storageFilePath = userImageRepository.findFilePathByOriginalFileNameAndFileName(originalName, filePath);

		return imageUploader.findImageInDirectory(originalName, storageFilePath);
	}

}
