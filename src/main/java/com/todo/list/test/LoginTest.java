package com.todo.list.test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.UserDTO;

@Controller
public class LoginTest {

	@PostMapping(value = "/test/login")
	public UserDTO loginTest(@ModelAttribute UserDTO dto) {

		System.out.println("접속 성공");
		return dto;
	}

	@PostMapping("/loginTest")
	public String test(@ModelAttribute String string, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, HttpSession httpSession) {
		String session_key = httpServletRequest.getSession().toString();
		
		if (httpSession.getAttribute("1234") == null) {
			httpSession.setAttribute("1234", httpServletRequest.getSession());
			httpServletResponse.addCookie(new Cookie("session", session_key));
			
		} else {
			System.out.println("세션있음");
		}
		System.out.println("들어왔음");
		return "redirect:http://127.0.0.1:5501/test2.html";
	}

}
