package com.todo.list.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.TodoCommentRepository;
import com.todo.list.repository.TodoImageRepository;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserRepository;

/**
 * 
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 */

@Service
public class TodoService {

	private UserRepository userRepository;
	private TodoRepository todoRepository;
	private TodoCommentRepository todoCommentRepository;
	private TodoImageRepository todoImageRepository;

	@Autowired
	public TodoService(UserRepository userRepository, TodoRepository todoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}

	@Transactional
	public TodoEntity todoSave(UserTokenDTO dto, TodoDTO todoDTO) {
		UserEntity user = userRepository.findByUsername(dto.getUsername());
		long defaultHeartValue = 0;
		Publish publish = Publish.PUBLISH;

		if (todoDTO.getIsPublish().equals("private")) {
			publish = Publish.PRIVATE;
		}
		TodoEntity entity = todoRepository
				.save(new TodoEntity(user, todoDTO.getTitle(), todoDTO.getContent(), defaultHeartValue, publish));

		if (todoDTO.getOriginalFileName() != null) {
			todoImageRepository.save(new TodoImageEntity(entity, todoDTO.getFileName(), todoDTO.getOriginalFileName(),
					todoDTO.getFilePath()));
		}

		return entity;
	}

	@Transactional
	public TodoEntity todoUpdate(TodoDTO todoDTO) {
		TodoEntity entity = todoRepository.findTodoEntityById(todoDTO.getId());
		entity.setContent(todoDTO.getContent());
		entity.setTitle(todoDTO.getTitle());

		if (todoDTO.getIsPublish().equals("private")) {
			entity.setIsPublish(Publish.PRIVATE);
		}

		return todoRepository.save(entity);
	}

	@Transactional
	public void todoDelete(Long id) {
		todoRepository.deleteById(id);
	}

	@Transactional
	public void updatePublished(Long id, String username) {
		TodoEntity entity = todoRepository.findById(id).get();
		if (entity.getIsPublish().equals(Publish.PUBLISH)) {
			entity.setIsPublish(Publish.PRIVATE);
		} else {
			entity.setIsPublish(Publish.PUBLISH);
		}
		todoRepository.save(entity);

	}

	/**
	 * 
	 * @param todo Id
	 */

	@Transactional
	public void addHeartUserTodo(Long id) {

	}

	/**
	 * 
	 * @param todoId, Comment DTO
	 */

	@Transactional
	public void addCommentUserTodo(Long id) {

	}

}
