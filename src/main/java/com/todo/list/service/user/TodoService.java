package com.todo.list.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.CommentDTO;
import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoImageEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.TodoImageRepository;
import com.todo.list.repository.todo.TodoCommentRepository;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.upload.TodoImageUploadService;
import com.todo.list.service.image.user.TodoImageService;

/**
 * 
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 * 
 */

@Service
public class TodoService {

	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private UserRepository userRepository;
	private TodoRepository todoRepository;
	private TodoImageRepository todoImageRepository;
	private ImageUploadService imageService;

	@Autowired
	private TodoImageService todoImageService;

	@Autowired
	public TodoService(EntityManager entityManager, UserRepository userRepository, TodoRepository todoRepository,
			TodoImageRepository todoImageRepository) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
		this.todoImageRepository = todoImageRepository;
		imageService = new TodoImageUploadService();
	}

	/**
	 * 
	 * @param dto
	 * @param todoDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public TodoEntity saveTodo(UserTokenDTO dto, TodoDTO todoDTO, MultipartFile[] todoImages) {
		UserEntity user = userRepository.findByUsername(dto.getUsername());

		long defaultHeartValue = 0;
		Publish publish = Publish.PUBLISH;

		if (todoDTO.getIsPublish().equals("private")) {
			publish = Publish.PRIVATE;
		}

		TodoEntity entity = todoRepository
				.save(new TodoEntity(user, todoDTO.getTitle(), todoDTO.getContent(), defaultHeartValue, publish));

		if (todoImages != null && todoImages.length != 0) {

			for (int i = 0; i < todoImages.length; i++) {
				MultipartFile data = todoImages[i];
				ImageDTO imageDTO = imageService.saveImageInDir(data);
				todoImageService.todoImageSave(entity, imageDTO);
			}
		}

		return entity;
	}

	/**
	 * 
	 * @param id
	 * @param todoDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public TodoEntity updateTodo(Long id, TodoDTO todoDTO) {
		TodoEntity entity = todoRepository.findTodoEntityById(id);

		String title = todoDTO.getTitle();
		String content = todoDTO.getContent();

		if (todoDTO.getIsPublish().equals("private")) {
			entity.setIsPublish(Publish.PRIVATE);
		}

		if (title != null) {
			entity.setTitle(title);
		}

		if (content != null) {
			entity.setContent(content);
		}

		return todoRepository.save(entity);
	}

	/**
	 * 
	 * @param id
	 * 
	 */

	@Transactional
	public void deleteTodo(Long id) {
		todoRepository.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 * @param username
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public void updateTodoPublished(Long id, String username) {
		TodoEntity entity = todoRepository.findById(id).get();
		if (entity.getIsPublish().equals(Publish.PUBLISH)) {
			entity.setIsPublish(Publish.PRIVATE);
		} else {
			entity.setIsPublish(Publish.PUBLISH);
		}
		todoRepository.save(entity);

	}

	/**
	 * UPDATE QUERY
	 * 
	 * @param todo Id
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public int addTodoHeart(Long id) {
		CriteriaUpdate<TodoEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(TodoEntity.class);
		Root<TodoEntity> root = criteriaUpdate.from(TodoEntity.class);

		criteriaUpdate.set("HEART", criteriaBuilder.sum(root.get("HEART"), 1));
		criteriaUpdate.where(criteriaBuilder.equal(root.get("TODO_ID"), id));

		int result = entityManager.createQuery(criteriaUpdate).executeUpdate();

		return result;

	}

}
