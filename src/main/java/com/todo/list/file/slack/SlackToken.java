package com.todo.list.file.slack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SlackToken {

	private static String slackToken;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value(value = "${slack.token.location}")
	public void setSlackToken(String tokeLocation) {
		String result = "";
		File files[] = new File(tokeLocation).listFiles();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(files[0]);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Slack Token Value => {}", result);
		slackToken = result;
	}

	public String getSlackToken() {
		return slackToken;
	}
}
