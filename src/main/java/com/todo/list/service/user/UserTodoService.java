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
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.UserTodoRepository;
import com.todo.list.repository.UserRepository;

@Service
public class UserTodoService {

	private UserRepository userRepository;
	private UserTodoRepository todoRepository;

	@Autowired
	public UserTodoService(UserRepository userRepository, UserTodoRepository todoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}

	@Transactional
	public UserTodoEntity todoSave(UserTokenDTO dto, TodoDTO todoDTO) {
		UserEntity user = userRepository.findByUsername(dto.getUsername());
		long defaultHeartValue = 0;
		Publish publish = Publish.PUBLISH.PUBLISH;

		if (todoDTO.getIsChekcPuhlic().equals("private")) {
			publish = Publish.PRIVATE;
		}
		return todoRepository
				.save(new UserTodoEntity(user, todoDTO.getTitle(), todoDTO.getContent(), defaultHeartValue, publish));
	}

	@Transactional
	public UserTodoEntity todoUpdate(TodoDTO todoDTO) {
		UserTodoEntity entity = todoRepository.findTodoEntityById(todoDTO.getId());
		entity.setContent(todoDTO.getContent());
		entity.setTitle(todoDTO.getTitle());

		if (todoDTO.getIsChekcPuhlic().equals("private")) {
			entity.setIsPublish(Publish.PRIVATE);
		}

		return todoRepository.save(entity);
	}

	@Transactional
	public void todoDelete(Long id) {
		todoRepository.deleteById(id);
	}

//	@Transactional
//	public void addRecommand(UserTokenDTO dto, Long id) {
//		TodoEntity entity = todoRepository.findById(id).get();
//		entity.setRecommand(entity.getRecommand() + 1);
//		todoRepository.save(entity);
//	}

	@Transactional
	public void updatePublished(Long id, String username) {
		UserTodoEntity entity = todoRepository.findById(id).get();
		if (entity.getIsPublish().equals(Publish.PUBLISH)) {
			entity.setIsPublish(Publish.PRIVATE);
		} else {
			entity.setIsPublish(Publish.PUBLISH);
		}
		todoRepository.save(entity);

	}

//	// todo api로 옮김
//	@Transactional
//	public List<UserTodoEntity> findAllPublishTodos() {
//
//		return todoRepository.findAllEntitiesByIsPublish(Publish.PUBLISH);
//	}
//
//	@Transactional(readOnly = true)
//	public Page<UserTodoEntity> publishTodos(int pageNumber, Pageable pageable) {
//		return todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
//	}
//
//	@Transactional(readOnly = true)
//	public Page<UserTodoEntity> recommandTodos(Pageable pageable) {
//
//		Page<UserTodoEntity> pages = todoRepository.findTodoEntitiesByIsPublishOrderByIdDesc(Publish.PUBLISH, pageable);
//
//		return pages;
//	}

//	//
//	// exist 필요
//	@Autowired
//	private RedisCacheManager redisCacheManager;
//
//	@Transactional(readOnly = true)
//	public TodoEntity findOne(Long id) {
//		System.out.println("ID : " + id);
//		String cacheName = redisCacheManager.getCache(String.valueOf(id)).getName();
//		System.out.println(cacheName);
//		return todoRepository.findById(id).get();
//	}
//
//	@Transactional(readOnly = true)
//	public TodoEntity findOneTestNotCacheAnnotation(Long id) {
//		System.out.println("ID : " + id);
//		Cache cache = redisCacheManager.getCache(String.valueOf(id));
//
//		return todoRepository.findById(id).get();
//	}
//	@Transactional
//	public TodoEntity todoSaveTest(TodoDTO todoDTO) {
//		
//		if(todoDTO.getIsChekcPuhlic() == Publish.PUBLISH.toString()) {
//			
//		}
//		return todoRepository.save(new TodoEntity(todoDTO.getTitle(), todoDTO.getContent()));
//	}

}
