package com.todo.list.test;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.domain.ImageEntity;

@ResponseBody
@RestController
@RequestMapping("/test")
public class APITest {

	@PostMapping("/set")
	public HttpSession setSessionTest(HttpSession httpSession) {

		httpSession.setAttribute("hi", "donghyeon");
		return httpSession;
	}

	@PostMapping("/get")
	public String getSessionTest(HttpSession httpSession) {
		String session = (String) httpSession.getAttribute("hi");

		return session;
	}

	@PostMapping("/header")
	public HttpHeaders headerTest(@RequestHeader HttpHeaders headers) {
		System.out.println(headers);
		return headers;
	}

	@GetMapping("/modelTest")
	public ModelAndView modelTest(ModelAndView modelAndView) {
		modelAndView.addObject("hi", "hello");
		
		return modelAndView;
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
		//ImageEntity entity = new ImageEntity(1,4);	
	}

}
