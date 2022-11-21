package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.mapper.TodoMapper;
import com.todo.list.repository.quote.QuoteLikeRepository;
import com.todo.list.repository.todo.TodoLikeRepository;

@Service
public class UserLikeApiService {

	private TodoLikeRepository todoLikeRepository;
	private QuoteLikeRepository quoteLikeRepository;

	@Autowired
	public UserLikeApiService(TodoLikeRepository todoLikeRepository, QuoteLikeRepository quoteLikeRepository) {
		this.todoLikeRepository = todoLikeRepository;
		this.quoteLikeRepository = quoteLikeRepository;
	}

	/**
	 * 
	 * @param userTokenDTO
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<TodoMapper> userLikeTodo(UserTokenDTO userTokenDTO, Pageable pageable) {

		Long userid = userTokenDTO.getId();
		String email = userTokenDTO.getEmail();
		Page<TodoMapper> page = todoLikeRepository.findUserLikeTodoByUserIdAndEmail(userid, email, pageable);

		return page;
	}

	/**
	 * 
	 * @param userTokenDTO
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	public Page<QuoteMapper> userLikeQuote(UserTokenDTO userTokenDTO, Pageable pageable) {

		Long userid = userTokenDTO.getId();
		String email = userTokenDTO.getEmail();
		Page<QuoteMapper> page = quoteLikeRepository.findUserLikeQuoteByUserIdAndEmail(userid, email, pageable);

		return page;
	}
}
