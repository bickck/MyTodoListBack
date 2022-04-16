package com.todo.list.test;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.UserDTO;

@Controller
public class LoginTest {

	@PostMapping(value = "/test/login")
	public UserDTO loginTest(@ModelAttribute UserDTO dto) {

		System.out.println("접속 성공");
		return dto;
	}

	@PostMapping("/test")
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

	@ResponseBody
	@PostMapping("/loginTest")
	public String loginTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			HttpSession httpSession) {
		
		UserDTO userDTO = new UserDTO("user1234", "password1234");
		
		HttpSession session = httpServletRequest.getSession();
		httpSession.setAttribute(session.getId(), userDTO);
		System.out.println();

		// System.out.println(session.getId());

		Enumeration<String> reqSession = httpSession.getAttributeNames();
		System.out.println(reqSession.hasMoreElements());

		while (reqSession.hasMoreElements()) {
			String name = reqSession.nextElement();
			System.out.println("Session Name :" + name);
			System.out.println("Session Value :" + httpSession.getAttribute(name).toString());
		}
		return "success";
	}

}
