package com.todo.list.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.MessageEntity;
import com.todo.list.repository.event.EventMessageRepository;

@Service
public class MessageApiService {

	@Autowired
	private EventMessageRepository eventMessageRepository;

	public List<MessageEntity> findReceviedMessageByUserActions(UserTokenDTO userTokenDTO) {

		Long userId = userTokenDTO.getId();
		
		return eventMessageRepository.findMessageByUserId(userId);
	}
}
