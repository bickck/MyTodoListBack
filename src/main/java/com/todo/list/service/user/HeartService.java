package com.todo.list.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.QuoteHeartEntity;
import com.todo.list.entity.TodoHeartEntity;
import com.todo.list.repository.TodoHeartRepository;
import com.todo.list.repository.quote.QuoteHeartRepository;

@Service
public class HeartService {

	@Autowired
	private TodoHeartRepository todoHeartRepository;

	@Autowired
	private QuoteHeartRepository quoteHeartRepository;

	/**
	 * 
	 * @param id
	 */

	@Transactional
	public void saveTodoHeart(Long id, UserTokenDTO userTokenDTO) {

		TodoHeartEntity todoHeartEntity = new TodoHeartEntity();
		
		todoHeartRepository.save(todoHeartEntity);
	}

	/**
	 * 
	 * @param id
	 */
	
	@Transactional
	public void cancleTodoHeart(Long id, UserTokenDTO userTokenDTO) {

		todoHeartRepository.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 */

	@Transactional
	public void saveQuoteHeart(Long id, UserTokenDTO userTokenDTO) {

		QuoteHeartEntity quoteHeartEntity = new QuoteHeartEntity();
		
		quoteHeartRepository.save(quoteHeartEntity);
	}

	/**
	 * 
	 * @param id
	 */

	@Transactional
	public void cancleQuoteHeart(Long id, UserTokenDTO userTokenDTO) {
		
		quoteHeartRepository.deleteById(id);

	}

}
