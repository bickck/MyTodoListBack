package com.todo.list.service.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.UserTodoRepository;

@Service
public class UserTodoService {

	private UserTodoRepository repository;

	@Autowired
	public UserTodoService(UserTodoRepository todoRepository) {
		this.repository = todoRepository;
	}

	@Transactional
	public void todoSave(UserTokenDTO dto, TodoDTO todoDTO) {

		repository.save(new TodoEntity(null, null, null));
	}

	@Transactional
	public void todoUpdate(UserTokenDTO dto, TodoDTO todoDTO, Long id) {
		TodoEntity entity = repository.findById(id).get();
		entity.setContent(todoDTO.getContent());
		entity.setTitle(todoDTO.getTitle());
		repository.save(entity);
	}

	@Transactional
	public void todoDelete(UserTokenDTO dto, Long id) {
		repository.deleteById(id);
	}

	@Transactional
	public void addRecommand(UserTokenDTO dto, Long id) {
		TodoEntity entity = repository.findById(id).get();
		entity.setRecommand(entity.getRecommand() + 1);
		repository.save(entity);
	}

	@Transactional
	public void updatePublished(UserTokenDTO dto, Long id) {
		TodoEntity entity = repository.findById(id).get();

	}
}
