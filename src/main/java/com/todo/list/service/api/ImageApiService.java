package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.repository.TodoImageRepository;
import com.todo.list.repository.UserImageRepository;
import com.todo.list.repository.mapper.ImageMapper;

@Service
public class ImageApiService {

	private TodoImageRepository todoImageRepository;
	private UserImageRepository userImageRepository;

	@Autowired
	public ImageApiService(TodoImageRepository todoImageRepository, UserImageRepository userImageRepository) {
		this.todoImageRepository = todoImageRepository;
		this.userImageRepository = userImageRepository;
	}

	/**
	 * Todo 이미지 가져오는 메소드
	 * 
	 * @param TodoId
	 * @return
	 */

	public List<ImageMapper> findTodoImageByTodoId(Long id) {

		return todoImageRepository.findTodoImageById(id);
	}

	/**
	 * User 이미지 가져오는 메소드
	 * 
	 * @param UserId
	 * @return
	 */

	public ImageMapper findUserImageByUserId(Long id) {

		return userImageRepository.findUserIntroImageById(id);
	}
}
