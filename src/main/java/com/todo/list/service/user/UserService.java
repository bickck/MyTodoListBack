package com.todo.list.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.TodoRepository;
import com.todo.list.repository.UserImageRepository;
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
	private UserImageRepository userImageRepository;

	@Autowired
	private UserUtil userUtil;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void userSave(UserDTO userDTO) {
		String email = userDTO.getEmail();
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();

		if (userUtil.isCheckDuplicatedEmail(email)) {
			String passwordEncode = userUtil.bCrypt(password);
			UserEntity userEntity = userRepository.save(new UserEntity(email, username, passwordEncode));
			userImageRepository.save(new UserImageEntity(userEntity, "", "", "", (long) 0));
		} else {
			throw new IllegalAccessError("중복된 아이디입니다.");
		}
	}

	@Transactional
	public UserEntity userUpdate(UserEntity user) {
		UserEntity userEntityFromDB = userRepository.getById(user.getId());

		userEntityFromDB.setIntroComment(user.getIntroComment());

		return userRepository.save(userEntityFromDB);
	}

	@Transactional
	public void userDelete(Long id) {
		// userRepository.deleteByUsernameAndPassword(userDTO.getUsername(),
		// userDTO.getPassword());
		userRepository.deleteById(id);
	}

	// 이메일 확인 및 비밀번호 확인 로직 필요

	@Transactional
	public UserEntity userLogin(UserDTO requestUserArg) throws AuthenticationException {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail());

		String password = requestUserArg.getPassword();
		String encPassword = user.getPassword();

		if (!userUtil.isMatch(password, encPassword) && user != null) {
			throw new AuthenticationException("이메일 및 비밀번호가 맞지 않습니다.");
		}

		return user;
	}

}
