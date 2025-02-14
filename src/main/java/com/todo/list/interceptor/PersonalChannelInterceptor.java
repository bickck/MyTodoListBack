package com.todo.list.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.todo.list.redis.service.MessageChannelService;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;

@Component
public class PersonalChannelInterceptor implements ChannelInterceptor {

	
	private MessageChannelService messageChannelService;
	private AuthenticationJwtProvider authenticationJwtProvider;

	@Autowired
	public PersonalChannelInterceptor(MessageChannelService messageChannelService,
			AuthenticationJwtProvider authenticationJwtProvider) {
		this.authenticationJwtProvider = authenticationJwtProvider;
		this.messageChannelService = messageChannelService;
	}

	/**
	 * 접속 세션은 headerAccessor에서 얻을 수 있음
	 */

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		// TODO Auto-generated method stub

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

		if (StompCommand.CONNECT.equals(headerAccessor.getCommand())) {

			requestConnectedAction(headerAccessor);

		}

		if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {

			requestSubscribeAction(headerAccessor);

		}

		if (StompCommand.DISCONNECT.equals(headerAccessor.getCommand())) {
			requestDisConnectAction(headerAccessor);
		}

		return message;
	}

	private void requestConnectedAction(StompHeaderAccessor headerAccessor) {

		String token = headerAccessor.getNativeHeader(HttpHeaders.AUTHORIZATION).get(0);
		String session = headerAccessor.getSessionId();

		Long userId = authenticationJwtProvider.resolveTokenToUserTokenDTO(token).getId();

		messageChannelService.saveChannel(userId, session);
	}

	private void requestSubscribeAction(StompHeaderAccessor headerAccessor) {
		String session = headerAccessor.getSessionId();

		System.out.println("Session : " + session);
	}

	private void requestDisConnectAction(StompHeaderAccessor headerAccessor) {

	}
}
