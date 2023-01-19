package com.todo.list.controller.main;

import javax.security.sasl.AuthenticationException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.ResponseStatus;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.controller.response.message.ResponseMessageEntity;
import com.todo.list.controller.response.token.ResponseTokenEntity;
import com.todo.list.entity.base.PlatForm;
import com.todo.list.exception.custom.ArgumentValidException;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.redis.service.MessageChannelService;
import com.todo.list.service.EventMessageService;
import com.todo.list.service.user.UserAuthService;
import com.todo.list.util.auth.UserAuthToken;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;
import com.todo.list.util.validation.group.LoginAccessArgumentGroup;
import com.todo.list.util.validation.group.RegisterAccessArgumentGroup;

/**
 * 유저 로그인, 로그아웃, 비밀번호 찾기, 회원가입을 제공하는 클래스
 * 
 * @author 3d193
 *
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthenticationJwtProvider authenticationJwtProvider;

	@Autowired
	private AuthRedisService authRedisService;

	@Autowired
	private UserAuthService authService;

	@Autowired
	private MessageChannelService messageChannelService;

	@Autowired
	private EventMessageService eventMessageService;

	@GetMapping("/test")
	public ResponseTokenEntity test() {

		return new ResponseTokenEntity();
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 * @throws AuthenticationException
	 */

	@ResponseBody
	@PostMapping(value = "/login")
	public ResponseTokenEntity requestLogin(
			@Validated(value = LoginAccessArgumentGroup.class) @RequestBody UserDTO userDTO,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ArgumentValidException(bindingResult.getFieldError());
		}

		String accessToken = authService.login(userDTO);

		return new ResponseTokenEntity(accessToken, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "/kakao/login")
	public ResponseTokenEntity requestKakaoLogin(@RequestBody UserDTO userDTO) throws Exception {

		String accessToken = authService.login(userDTO, PlatForm.KAKAO);

		return new ResponseTokenEntity(accessToken, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 */

	@ResponseBody
	@PostMapping(value = "/register")
	public synchronized ResponseMessageEntity<?> requestRegister(
			@Validated(value = RegisterAccessArgumentGroup.class) @RequestBody UserDTO userDTO,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new ArgumentValidException(bindingResult.getFieldError());
		}

		authService.register(userDTO);

		return new ResponseMessageEntity<String>(ResponseStatus.SUCCESS, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param userDTO
	 * @param dto
	 * @return
	 */

	@PostMapping("/logout")
	public ResponseMessageEntity<String> logoutRequest(
			@RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken) {

		String payLoad = authenticationJwtProvider.seperatorPayLoad(accessToken);
		UserTokenDTO userTokenDTO = authenticationJwtProvider.resolveTokenToUserTokenDTO(accessToken);

		authRedisService.deleteLoginUserToken(payLoad);
		eventMessageService.deleteUserMessageById(userTokenDTO.getId());
		messageChannelService.deleteUserChannelById(userTokenDTO.getId());

		return new ResponseMessageEntity<String>(ResponseStatus.SUCCESS, HttpStatus.OK);
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

	/**
	 * 
	 * @param userDTO
	 * @param dto
	 * @return
	 */

	@PutMapping("/permission/{username}")
	public ResponseMessageEntity<String> isCheckuserPermission(@RequestBody UserDTO userDTO,
			@UserAuthToken UserTokenDTO dto) {

		if (!userDTO.getUsername().equals(dto.getUsername())) {
			return new ResponseMessageEntity<String>(ResponseStatus.FAILURE, HttpStatus.ACCEPTED);
		}

		return new ResponseMessageEntity<String>(ResponseStatus.SUCCESS, HttpStatus.ACCEPTED);
	}

	/**
	 * 
	 * @param userDTO
	 * @param dto
	 * @return
	 */

	@PutMapping("/reissue")
	public ResponseEntity<String> renewAccessToken(@RequestBody UserDTO userDTO, @UserAuthToken UserTokenDTO dto) {

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	/**
	 * 
	 * @param id
	 * @param userDTO
	 * @return
	 */

	@PutMapping("/changePassword/{id}")
	public ResponseEntity<?> changePassword(@PathVariable Long id, UserDTO userDTO) {

		// userService.changeUserPassword(id, userDTO);

		return new ResponseEntity<>(ResponseStatus.SUCCESS, HttpStatus.OK);
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
