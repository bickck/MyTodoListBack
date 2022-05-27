package com.todo.list.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.todo.list.controller.UserController;
import com.todo.list.controller.dto.service.QuoteDTO;

//@WebMvcTest(UserController.class)
public class UserControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	public void saveUserQuote() throws Exception {
//		QuoteDTO dto = new QuoteDTO("quote", "author");
//		MultiValueMap<String, String> values = new LinkedMultiValueMap<String, String>();
//		values.add("author", "1234");
//		values.add("quote", "qqqqq");
//		mockMvc.perform(post("/user/manage/quote/1").params(values))
//		.andExpect(status().isOk())
//		.andExpect((ResultMatcher) content().string("성공"))
//		.andDo(print());
//	}

	@Test
	public void test() {
		File[] files = File.listRoots();
		for (File file : files) {
			System.out.println(file);
		}
	}

	@Test
	public void test2() throws IOException {
		String pathname = "E:\\img\\defaultImage\\8b956be74df2ac6c6c8d79d6046de6d577c0185816904f6ebc629382503e9a39.jpg";
		String type = Files.probeContentType(Path.of(pathname));

		List<String> lines = Files.readAllLines(Path.of("E:\\img\\defaultImage"));

		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i));
		}
	}
}
