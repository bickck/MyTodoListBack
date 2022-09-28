package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.TodoRepository;

@Service
public class TodoApiService {

	@Autowired
	private TodoRepository todoRepository;

	@Transactional(readOnly = true)
	public Page<TodoEntity> getPublishedTodos(Pageable pageable) {
		Page<TodoEntity> entities = todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);

		return entities;
	}

	@Transactional(readOnly = true)
	public Page<TodoEntity> getMostRecommandDaily(Pageable pageable) {
		Page<TodoEntity> entities = null;

		return entities;
	}
	
	@Transactional(readOnly = true)
	public List<TodoEntity> findAllPublishTodos() {
		
		return todoRepository.findAllEntitiesByIsPublish(Publish.PUBLISH);
	}

	@Transactional(readOnly = true)
	public Page<TodoEntity> publishTodos(int pageNumber, Pageable pageable) {
		return todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
	}

	@Transactional(readOnly = true)
	public Page<TodoEntity> recommandTodos(Pageable pageable) {

		Page<TodoEntity> pages = todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);

		return pages;
	}
}
