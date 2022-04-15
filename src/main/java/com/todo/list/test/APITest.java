package com.todo.list.test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.domain.ImageEntity;
import com.todo.list.security.LoginResponseToken;

@ResponseBody
@RestController
@RequestMapping("/test")
public class APITest {
	private UserDTO user = new UserDTO("1234", "567898");

	@GetMapping("/set")
	public String setSessionTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			HttpSession httpSession) {

		LoginResponseToken loginResponseToken = new LoginResponseToken();

		return loginResponseToken.token(user);
	}

	@GetMapping("/get")
	public String getSessionTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			HttpSession httpSession) {
		Cookie[] cookeis = httpServletRequest.getCookies();
		if (cookeis == null) {
			System.out.println("쿠키 있음");
			return "세션 없음";
		}

		return "success";
	}

//	데이터 얻은 곳

	@PostMapping("/json")
	public String getJsonTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			HttpSession httpSession) {

		System.out.println("요청 들어옴");
		Cookie[] cookeis = httpServletRequest.getCookies();
		System.out.println(cookeis);
		if (cookeis != null) {
			return "쿠키 있음";
		}
		return "쿠키 없음";
	}

	@PostMapping("/header")
	public HttpHeaders headerTest(@RequestHeader HttpHeaders headers) {
		System.out.println(headers);
		return headers;
	}

	@PostMapping("/valid")
	public String validTest(@RequestBody @Valid UserDTO dto, BindingResult bindingResult) {
		System.out.println(bindingResult.hasErrors());

		return bindingResult.getNestedPath();
	}

	@GetMapping("/nullEx")
	public void nullPointExceptionTest() {
		throw new NullPointerException();
	}

	@ExceptionHandler(NullPointerException.class)
	public Object exceptionHandler(NullPointerException e) {
		System.out.println("exceptionHandler()");
		return e.getClass();
	}

	public void lombokTest() {
		// ImageEntity entity = new ImageEntity(1,4);
	}

}
