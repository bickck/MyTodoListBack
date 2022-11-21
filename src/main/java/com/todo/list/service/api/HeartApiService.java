package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.HeartDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.quote.QuoteHeartRepository;
import com.todo.list.repository.todo.TodoHeartRepository;

@Service
public class HeartApiService {

	@Autowired
	private QuoteHeartRepository quoteHeartRepository;

	@Autowired
	private TodoHeartRepository todoHeartRepository;

	@Transactional(readOnly = true)
	public HeartDTO existsTodoHeart(Long id, UserTokenDTO userTokenDTO) {

		String todoHeartUUID = null;
		boolean exists = todoHeartRepository.existsByTodoEntityIdAndUserId(id, userTokenDTO.getId());

		if (exists) {
			todoHeartUUID = todoHeartRepository.findTodoUUID(id, userTokenDTO.getId());
		}

		return new HeartDTO(todoHeartUUID, exists);
	}

	@Transactional(readOnly = true)
	public HeartDTO existsQuoteHeart(Long id, UserTokenDTO userTokenDTO) {

		String quoteHeartUUID = null;
		boolean exists = quoteHeartRepository.existsByQuoteEntityIdAndUserId(id, userTokenDTO.getId());

		if (exists) {
			quoteHeartUUID = quoteHeartRepository.findQuoteUUID(id, userTokenDTO.getId());
		}

		return new HeartDTO(quoteHeartUUID, exists);
	}
}
