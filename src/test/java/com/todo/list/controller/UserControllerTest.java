package com.todo.list.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
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
import com.todo.list.controller.dto.QuoteDTO;


@WebMvcTest(UserController.class)
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
}
