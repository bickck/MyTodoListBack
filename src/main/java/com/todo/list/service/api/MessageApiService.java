package com.todo.list.service.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.event.EventMessageRepository;

public class MessageApiService {

	@Autowired
	private EventMessageRepository eventMessageRepository;

	public void findReceviedMessageByUserActions(UserTokenDTO userTokenDTO) {

	}
}
