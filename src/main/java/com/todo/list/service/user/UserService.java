package com.todo.list.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.domain.UserEntity;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.repository.UserRepository;
import com.todo.list.service.util.UserUtil;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저의 로그인, 회원가입 유저의 정보 수정등을 돕기위한 문서입니다.
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserUtil userUtil;

	public void userSave(UserDTO userDTO) {
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();

		System.out.println("UserService" + userDTO.toString());
		if (userUtil.isUsernameDuplicatedCheck(username)) {
//			String passwordEncode = userUtil.passwordEncoding(password);
			userRepository.save(new UserEntity(username, password));
		} else {
			// 중복 예외 발생
			// trows 중복 코드 발생
		}
	}

	public UserEntity userLogin(UserDTO userDTO) {

		return userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
	}
}
