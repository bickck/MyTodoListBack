package com.todo.list.service.user;

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

import com.todo.list.controller.dto.CommentDTO;
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

	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private UserRepository userRepository;
	private TodoRepository todoRepository;
	private TodoCommentRepository todoCommentRepository;
	private TodoImageRepository todoImageRepository;

	@Autowired
	public TodoService(EntityManager entityManager, UserRepository userRepository, TodoRepository todoRepository,
			TodoCommentRepository todoCommentRepository, TodoImageRepository todoImageRepository) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
		this.todoCommentRepository = todoCommentRepository;
		this.todoImageRepository = todoImageRepository;
	}

	@Transactional
	public TodoEntity saveTodo(UserTokenDTO dto, TodoDTO todoDTO) {
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
	public TodoEntity updateTodo(TodoDTO todoDTO) {
		TodoEntity entity = todoRepository.findTodoEntityById(todoDTO.getId());

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

	@Transactional
	public void deleteTodo(Long id) {
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
	 * UPDATE QUERY
	 * 
	 * @param todo Id
	 */

	@Transactional
	public int addHeartUserTodo(Long id) {
		CriteriaUpdate<TodoEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(TodoEntity.class);
		Root<TodoEntity> root = criteriaUpdate.from(TodoEntity.class);

		criteriaUpdate.set("HEART", criteriaBuilder.sum(root.get("HEART"), 1));
		criteriaUpdate.where(criteriaBuilder.equal(root.get("TODO_ID"), id));

		int result = entityManager.createQuery(criteriaUpdate).executeUpdate();

		return result;

	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @param commentDTO
	 * @return
	 */

	@Transactional
	public TodoCommentEntity saveTodoComment(Long id, UserTokenDTO userTokenDTO, CommentDTO commentDTO) {

		return todoCommentRepository.save(null);
	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @param commentDTO
	 */

	@Transactional
	public int updateTodoComment(Long id, UserTokenDTO userTokenDTO, CommentDTO commentDTO) {

		CriteriaUpdate<TodoCommentEntity> criteriaUpdate = criteriaBuilder
				.createCriteriaUpdate(TodoCommentEntity.class);

		Root<TodoCommentEntity> root = criteriaUpdate.from(TodoCommentEntity.class);

		criteriaUpdate.set("Comment", commentDTO.getComment());
		criteriaUpdate.where(criteriaBuilder.equal(root.get("todo"), id));

		int result = entityManager.createQuery(criteriaUpdate).executeUpdate();

		return result;

	}

	/**
	 * 
	 * @param id
	 */

	@Transactional
	public void deleteTodoComment(Long id) {
		todoCommentRepository.deleteById(id);
	}

}
