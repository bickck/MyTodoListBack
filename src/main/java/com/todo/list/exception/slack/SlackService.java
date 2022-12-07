package com.todo.list.exception.slack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;

@Service
public class SlackService {
	
	
	@Value(value = "${slack.token}")
	private String token;
	
	@Value(value = "${slack.channel.monitor}")
	private String channel;

	
	public void postSlackMessage(String message) {
		
		try {
			MethodsClient methods = Slack.getInstance().methods(token);
			ChatPostMessageRequest request = 
					ChatPostMessageRequest.builder()
					.channel(channel)
					.text(message)
					.build();
			
			methods.chatPostMessage(request);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
