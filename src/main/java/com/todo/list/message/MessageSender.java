package com.todo.list.message;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.todo.list.entity.UserEntity;
import com.todo.list.redis.service.MessageChannelService;
import com.todo.list.service.EventMessageService;

@Service
public class MessageSender {

	@Autowired
	private EventMessageService eventMessageService;

	@Autowired
	private MessageChannelService messageChannelService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	public void sendTodoHeartEventMessage(Long id, String message) {

		UserEntity userEntity = eventMessageService.findUserEntityByTodoId(id);

		isExsistLoginUserOnServer(userEntity, message);

	}

	public void sendQuoteHeartEventMessage(Long id, String message) {
		UserEntity userEntity = eventMessageService.findUserEntityByQuoteId(id);

		isExsistLoginUserOnServer(userEntity, message);
	}
	
	/**
	 * 유저가 로그인을 하지 않았다면 Message DB에 저장
	 * 
	 * @param userEntity
	 * @param message
	 */

	private void isExsistLoginUserOnServer(UserEntity userEntity, String message) {
		
		if (messageChannelService.isExsistUserInRedis(userEntity.getId())) {
			String userPersonalChannelName = userEntity.getPersonalMessageChannelName();
			sendMessage(userPersonalChannelName, message);
		} else {
			eventMessageService.saveMessage(message, userEntity.getId());
		}
	}
	
	/**
	 * 해당 유저에 메시지를 보냄
	 * 
	 * @param userPrivateChannel
	 * @param message
	 */

	private void sendMessage(String userPrivateChannel, String message) {

		if (userPrivateChannel == null) {
			return;
		}
		if (message.equals(null)) {
			return;
		}

		HashMap<String, String> repMessage = new HashMap<String, String>();

		repMessage.put("message", message);

		messagingTemplate.convertAndSend(userPrivateChannel, repMessage);
	}
}
