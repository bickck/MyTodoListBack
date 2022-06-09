package com.todo.list.service.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.Publish;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserRepository;

@Service
public class UserTodoService {

	private UserRepository userRepository;
	private TodoRepository todoRepository;

	@Autowired
	public UserTodoService(UserRepository userRepository, TodoRepository todoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}

	@Transactional
	public void todoSave(UserTokenDTO dto, TodoDTO todoDTO) {
		UserEntity entity = userRepository.findByUsername(dto.getUsername());
		todoRepository.save(new TodoEntity(entity, todoDTO.getTitle(), todoDTO.getContent()));
	}

	@Transactional
	public void todoUpdate(Long id,TodoDTO todoDTO) {
		TodoEntity entity = todoRepository.findTodoEntityById(id);
		entity.setContent(todoDTO.getContent());
		entity.setTitle(todoDTO.getTitle());
		todoRepository.save(entity);
	}

	@Transactional
	public void todoDelete(UserTokenDTO dto, Long id) {
		todoRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Page<TodoEntity> publishTodos(int pageNumber, Pageable pageable) {
		return todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
	}

	@Transactional(readOnly = true)
	public Page<TodoEntity> recommandTodos(Pageable pageable) {
		return todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
	}

	@Transactional
	public void addRecommand(UserTokenDTO dto, Long id) {
		TodoEntity entity = todoRepository.findById(id).get();
		entity.setRecommand(entity.getRecommand() + 1);
		todoRepository.save(entity);
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

	@Transactional
	public void updatePublishedTest(Long id) {
		TodoEntity entity = todoRepository.findById(id).get();
		if (entity.getIsPublish().equals(Publish.PUBLISH)) {
			entity.setIsPublish(Publish.PRIVATE);
		} else {
			entity.setIsPublish(Publish.PUBLISH);
		}
		todoRepository.save(entity);

	}
}
