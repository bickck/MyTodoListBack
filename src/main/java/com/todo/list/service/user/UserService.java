package com.todo.list.service.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.list.domain.UserEntity;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.repository.UserRepository;
import com.todo.list.util.UserUtil;

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

	@PostConstruct
	public void init() {
		userRepository.save(new UserEntity("1234", "1234"));
		userRepository.save(new UserEntity("user1234", "password1234"));
		userRepository.save(new UserEntity("user2345", "password4141"));
		userRepository.save(new UserEntity("user4444", "password23231"));
		userRepository.save(new UserEntity("user2222", "passwordafeee"));
	}

	public void userSave(UserDTO userDTO) {
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();

		System.out.println("UserService" + userDTO.toString());
		if (userUtil.isUsernameDuplicatedCheck(username)) {
//			String passwordEncode = userUtil.passwordEncoding(password);
			userRepository.save(new UserEntity(username, password));
		} else {
			throw new IllegalAccessError();
		}
	}

//	public UserEntity userUpdate() {
//		return null;
//	}

	public void userDelete() {

	}

	public UserEntity userLogin(UserDTO userDTO) {

		return userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
	}

}
