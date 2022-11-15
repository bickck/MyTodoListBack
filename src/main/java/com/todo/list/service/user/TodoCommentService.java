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
import com.todo.list.repository.todo.TodoCommentRepository;

@Service
public class TodoCommentService {

	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private TodoCommentRepository todoCommentRepository;

	@Autowired
	public TodoCommentService(EntityManager entityManager, TodoCommentRepository todoCommentRepository) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.todoCommentRepository = todoCommentRepository;

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

		return todoCommentRepository.save(null);
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
