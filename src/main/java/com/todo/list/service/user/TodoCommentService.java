package com.todo.list.service.user;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.CommentDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.TodoCommentEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.todo.TodoCommentRepository;

@Service
public class TodoCommentService {

	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private TodoCommentRepository todoCommentRepository;
	private TodoRepository todoRepository;
	private UserRepository userRepository;

	@Autowired
	public TodoCommentService(EntityManager entityManager, TodoCommentRepository todoCommentRepository,
			TodoRepository todoRepository, UserRepository userRepository) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.todoCommentRepository = todoCommentRepository;
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;

	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @param commentDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public TodoCommentEntity saveTodoComment(Long id, UserTokenDTO userTokenDTO, CommentDTO commentDTO) {

		TodoEntity todoEntity = todoRepository.findTodoEntityById(id);

		UserEntity userEntity = userRepository.findById(userTokenDTO.getId()).get();

		TodoCommentEntity commentEntity = new TodoCommentEntity();

		commentEntity.setComment(commentDTO.getComment());

		commentEntity.setTodo(todoEntity);

		commentEntity.setUser(userEntity);

		return todoCommentRepository.save(commentEntity);
	}

	/**
	 * 
	 * @param id
	 * @param userTokenDTO
	 * @param commentDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
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
