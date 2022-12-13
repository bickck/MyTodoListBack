package com.todo.list.exception.slack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.todo.list.file.slack.SlackToken;

@Service
public class SlackService {

	@Autowired
	private SlackToken slackToken;

	@Value(value = "${slack.channel.monitor}")
	private String channel;

	public void postSlackMessage(String errorMessage, String cause) {

		String token = slackToken.getSlackToken();
		
		try {
			MethodsClient methods = Slack.getInstance().methods(token);
			ChatPostMessageRequest request = 
					ChatPostMessageRequest.builder()
					.channel(channel)
					.text("Error Message : " + errorMessage)
					.build();
			
			methods.chatPostMessage(request);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
