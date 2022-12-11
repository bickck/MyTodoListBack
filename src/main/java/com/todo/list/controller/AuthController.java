package com.todo.list.controller;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.CookieGenerator;

import com.todo.list.configs.token.AuthenticationJwt;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.service.user.UserService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.Utils;
import com.todo.list.util.auth.UserAuthToken;

/**
 * 유저 로그인, 로그아웃, 비밀번호 찾기, 회원가입을 제공하는 클래스
 * 
 * @author 3d193
 *
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private UserService userService;
	private UserUtil userUtil;
	private AuthenticationJwt jwtLoginToken;
	private Utils utils = new Utils();

	@Autowired
	public AuthController(UserUtil userUtil, UserService userService, AuthenticationJwt jwtLoginToken) {
		this.userUtil = userUtil;
		this.userService = userService;
		this.jwtLoginToken = jwtLoginToken;
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 * @throws AuthenticationException
	 */

	@ResponseBody
	@PostMapping(value = "/login")
	public ResponseEntity<String> requestLogin(@RequestBody UserDTO userDTO) throws AuthenticationException {

		if (userDTO == null) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.ACCEPTED);
		}
		if (userDTO.getEmail() == null) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.ACCEPTED);
		}

		if (userDTO.getPassword() == null) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.ACCEPTED);
		}

		UserEntity user = userService.userLogin(userDTO);
		String userToken = jwtLoginToken.makeToken(user);

		return new ResponseEntity<String>(userToken, HttpStatus.ACCEPTED);
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 */

	@ResponseBody
	@PostMapping(value = "/register")
	public synchronized ResponseEntity<String> requestRegister(@RequestBody UserDTO userDTO) {

		if (userDTO == null) {
			return new ResponseEntity<String>(ResponseStatus.FAILURE, HttpStatus.CREATED);
		}

		userService.userSave(userDTO);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param userDTO
	 * @param dto
	 * @return
	 */

	@PostMapping("/logout")
	public ResponseEntity<String> logoutRequest(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO dto) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	/**
	 * 
	 * @param userDTO
	 * @param dto
	 * @return
	 */

	@PutMapping("/findPassword")
	public ResponseEntity<String> findUserPassword(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO dto) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

//	@PostMapping("/login")
//	public String loginRequest(@ModelAttribute UserDTO userDTO, HttpSession httpSession,
//			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//
//		Cookie[] cookies = httpServletRequest.getCookies();
//
//		if (cookies == null || userUtil.isFindCookie(cookies, SEESION_NAME)) {
//
//			UserEntity entity = userService.userLogin(userDTO);
//			if (entity == null) {
//				throw new IllegalAccessError("로그인 해주세요");
//			}
//
//			HttpSession reqSession = httpServletRequest.getSession(); // 세션 생성
//			String sessionId = reqSession.getId(); // 세션 아이디 확인
//			System.out.println("생성된 Session ID : " + sessionId);
//
//			httpSession.setAttribute(sessionId, userDTO.getUsername()); // 세션 아이디를 이름으로 user정보 저장
//
//			CookieGenerator cookieGenerator = new CookieGenerator();
//			cookieGenerator.setCookieName(SEESION_NAME);
//			cookieGenerator.addCookie(httpServletResponse, sessionId); // 클라이언트에게 세션값을 쿠키로 반환
//		}
//		return "redirect:http://127.0.0.1:5501/index.html";
//	}

}
