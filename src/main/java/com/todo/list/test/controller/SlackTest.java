package com.todo.list.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.exception.slack.SlackService;

@RestController
public class SlackTest {

	@Autowired
	private SlackService slackService;

	@GetMapping(value = "/slack")
	public void slackTest() {

		slackService.postSlackMessage("Slack Test","slack Cause");
	}
}
