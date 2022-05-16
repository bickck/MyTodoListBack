package com.todo.list.service.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.repository.UserTodoRepository;

@Service
public class TodoService {

	private UserTodoRepository repository;

	@Autowired
	public TodoService(UserTodoRepository todoRepository) {
		this.repository = todoRepository;
	}

	@Transactional
	public void todoSave(UserTokenDTO dto, TodoDTO todoDTO) {

		repository.save(new UserTodoEntity(null, null, null));
	}

	@Transactional
	public void todoUpdate(UserTokenDTO dto, TodoDTO todoDTO) {
		repository.save(null);
	}

	@Transactional
	public void todoDelete(UserTokenDTO dto, Long id) {
		repository.deleteById(id);
	}
}
