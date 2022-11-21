package com.todo.list.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.QuoteHeartEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoHeartEntity;
import com.todo.list.entity.UserEntity;
import com.todo.list.repository.QuoteRepository;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.quote.QuoteHeartRepository;
import com.todo.list.repository.todo.TodoHeartRepository;
import com.todo.list.util.uuid.CommonUUID;

/**
 * 
 * @author 3d193
 *
 */

@Service
public class HeartService {

	private QuoteRepository quoteRepository;
	private TodoRepository todoRepository;
	private UserRepository userRepository;
	private TodoHeartRepository todoHeartRepository;
	private QuoteHeartRepository quoteHeartRepository;
	private CommonUUID commonUUID = new CommonUUID();

	@Autowired
	public HeartService(QuoteRepository quoteRepository, TodoRepository todoRepository, UserRepository userRepository,
			TodoHeartRepository todoHeartRepository, QuoteHeartRepository quoteHeartRepository) {
		// TODO Auto-generated constructor stub
		this.quoteRepository = quoteRepository;
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
		this.todoHeartRepository = todoHeartRepository;
		this.quoteHeartRepository = quoteHeartRepository;
	}

	/**
	 * 
	 * @param todo id
	 */

	@Transactional
	public String saveTodoHeart(Long id, UserTokenDTO userTokenDTO) {

		TodoHeartEntity todoHeartEntity = new TodoHeartEntity();
		String uuid = commonUUID.generatorCommentUUID();
		TodoEntity todoEntity = todoRepository.findById(id).get();
		UserEntity userEntity = userRepository.findById(userTokenDTO.getId()).get();

		todoHeartEntity.setUuid(uuid);
		todoHeartEntity.setTodoEntity(todoEntity);
		todoHeartEntity.setUser(userEntity);

		todoHeartRepository.save(todoHeartEntity);
		
		return uuid;
	}

	/**
	 * 
	 * @param todo id
	 */

	@Transactional
	public void cancleTodoHeart(String id, UserTokenDTO userTokenDTO) {

		todoHeartRepository.deleteByUuid(id);
	}

	/**
	 * 
	 * @param quote id
	 */

	@Transactional
	public void saveQuoteHeart(Long id, UserTokenDTO userTokenDTO) {

		QuoteHeartEntity quoteHeartEntity = new QuoteHeartEntity();

		QuoteEntity quoteEntity = quoteRepository.findById(id).get();
		UserEntity userEntity = userRepository.findById(userTokenDTO.getId()).get();

		quoteHeartEntity.setUuid(commonUUID.generatorCommentUUID());
		quoteHeartEntity.setQuoteEntity(quoteEntity);
		quoteHeartEntity.setUser(userEntity);

		quoteHeartRepository.save(quoteHeartEntity);
	}

	/**
	 * 
	 * @param quote id
	 */

	@Transactional
	public void cancleQuoteHeart(String id, UserTokenDTO userTokenDTO) {

		quoteHeartRepository.deleteByUuid(id);
	}

}
