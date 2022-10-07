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

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.service.TodoDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
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
	public TodoEntity todoSave(UserTokenDTO dto, TodoDTO todoDTO) {
		UserEntity entity = userRepository.findByUsername(dto.getUsername());
		return todoRepository.save(new TodoEntity(entity, todoDTO.getTitle(), todoDTO.getContent()));
	}
	
	@Transactional
	public TodoEntity todoSaveTest(TodoDTO todoDTO) {
		
		if(todoDTO.getIsChekcPuhlic() == Publish.PUBLISH.toString()) {
			
		}
		return todoRepository.save(new TodoEntity(todoDTO.getTitle(), todoDTO.getContent()));
	}

	@Transactional
	public TodoEntity todoUpdate(TodoEntity todoEntity) {
		TodoEntity entity = todoRepository.findTodoEntityById(todoEntity.getId());
		entity.setContent(todoEntity.getContent());
		entity.setTitle(todoEntity.getTitle());
		return todoRepository.save(entity);
	}

	@Transactional
	public void todoDelete(Long id) {
		todoRepository.deleteById(id);
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

	// todo api로 옮김
	@Transactional
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

	//
	// exist 필요
	@Autowired
	private RedisCacheManager redisCacheManager;

	@Transactional(readOnly = true)
	public TodoEntity findOne(Long id) {
		System.out.println("ID : " + id);
		String cacheName = redisCacheManager.getCache(String.valueOf(id)).getName();
		System.out.println(cacheName);
		return todoRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public TodoEntity findOneTestNotCacheAnnotation(Long id) {
		System.out.println("ID : " + id);
		Cache cache = redisCacheManager.getCache(String.valueOf(id));

		return todoRepository.findById(id).get();
	}
}
