package com.todo.list.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserQuoteEntity;
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

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void userSave(UserDTO userDTO) {
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();

		if (userUtil.isUsernameDuplicatedCheck(username)) {
			String passwordEncode = userUtil.bCrypt(password);
			userRepository.save(new UserEntity(username, passwordEncode));
		} else {
			throw new IllegalAccessError("중복된 아이디입니다.");
		}
	}

	public UserEntity userUpdate(UserDTO userDTO) {
		return userRepository.save(new UserEntity());
	}

	public void userDelete(UserDTO userDTO) {
		userRepository.deleteByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
	}

	public UserEntity userLogin(UserDTO userDTO) throws AuthenticationException {

		UserEntity user = userRepository.findByUsername(userDTO.getUsername());
		String password = userDTO.getPassword();
		String encPassword = user.getPassword();

		if (!userUtil.isMatch(password, encPassword)) {
			throw new AuthenticationException();
		}

		return user;
	}

}
