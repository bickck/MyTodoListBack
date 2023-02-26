package com.todo.list.service.main;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.PlatForm;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.repository.user.UserRepository;
import com.todo.list.service.image.logical.UserImageService;
import com.todo.list.service.message.GeneratorChannel;
import com.todo.list.util.UserUtil;

@Service
public class UserAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthRedisService authRedisService;

	@Autowired
	private UserImageService userImageService;

	@Autowired
	private GeneratorChannel generatorChannel;

	@Autowired
	private UserUtil userUtil;

	/**
	 * 
	 * @param requestUserArg
	 * @throws AuthenticationException
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public String login(UserDTO requestUserArg) throws Exception {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail())
				.orElseThrow(() -> new NullPointerException("존재하지 않는 아이디 입니다."));

		String password = requestUserArg.getPassword();
		String encPassword = user.getPassword();

		if (!userUtil.isMatch(password, encPassword)) {
			throw new NullPointerException("이메일 및 비밀번호가 맞지 않습니다.");
		}

		return authRedisService.saveLoginedUserToken(user);
	}

	/**
	 * other Platform login
	 * 
	 * @param requestUserArg
	 * @param platForm
	 * @return
	 * @throws AuthenticationException
	 */

	@Transactional
	public String login(UserDTO requestUserArg, PlatForm platForm) throws Exception {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail()).get();

		// 아이디가 존재하지 않을 경우
		if (user == null) {
			String personalChannel = generatorChannel.personalUserMessageChannel(requestUserArg.getId());
			user = new UserEntity();
			user.setEmail(requestUserArg.getEmail());
			user.setPassword("");
			user.setPlatform(platForm);
			user.setPersonalMessageChannelName(personalChannel);
			userRepository.save(user);
		}

		return authRedisService.saveLoginedUserToken(user);
	}

	/**
	 * 
	 * @param userDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public UserEntity register(UserDTO userDTO) {

		String email = userDTO.getEmail();
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();
		String passwordEncode = userUtil.bCrypt(password);
		String personalUserChannel = generatorChannel.personalUserMessageChannel(username);

		if (userRepository.existsByEmail(email)) {
			throw new NullPointerException("중복된 아이디입니다.");
		}

		UserEntity userEntity = userRepository
				.save(new UserEntity(email, username, passwordEncode, personalUserChannel, PlatForm.DEFAULT));

		return userImageService.saveRegistedUserImage(userEntity);
	}

	/**
	 * 
	 * @param id
	 * @param requestUserArg
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */
	@Transactional
	public UserEntity changeUserPassword(Long id, UserDTO requestUserArg) {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail())
				.orElseThrow(() -> new NullPointerException("존재하지 않는 아이디 입니다."));

		String encPassword = userUtil.bCrypt(requestUserArg.getPassword());
		user.setPassword(encPassword);

		return userRepository.save(user);
	}

}
