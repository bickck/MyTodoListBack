package com.todo.list.service.todo;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class UserTodoService {

	private TodoRepository repository;

	@Autowired
	public UserTodoService(TodoRepository todoRepository) {
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

	@Transactional(readOnly = true)
	public Page<TodoEntity> publishTodos(Pageable pageable) {
		return repository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
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
		if (entity.getIsPublish().equals(Publish.PUBLISH)) {
			entity.setIsPublish(Publish.PRIVATE);
		} else {
			entity.setIsPublish(Publish.PUBLISH);
		}
		repository.save(entity);

	}

}
