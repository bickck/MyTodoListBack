package com.todo.list.service.user;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.controller.dto.user.UserDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.UserImageEntity;
import com.todo.list.redis.service.AuthRedisService;
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.service.image.upload.UserImageUploadService;
import com.todo.list.service.image.user.UserImageService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.auth.provider.AuthenticationJwtProvider;
import com.todo.list.util.uuid.CommonUUID;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저의 로그인, 회원가입 유저의 정보 수정등을 제공하는 클래스
 */

@Service
public class UserService {

	private UserImageService userImageService;
	private UserRepository userRepository;
	private UserImageRepository userImageRepository;
	private UserImageUploadService imageUploadService;
	private AuthenticationJwtProvider jwtLoginToken;
	private AuthRedisService authRedisService;
	private UserUtil userUtil;

	@Autowired
	public UserService(UserImageService userImageService, UserRepository userRepository,
			UserImageRepository userImageRepository, UserUtil userUtil, UserImageUploadService imageUploadService,
			AuthenticationJwtProvider jwtLoginToken, AuthRedisService authRedisService) {
		// TODO Auto-generated constructor stub
		this.userImageService = userImageService;
		this.userRepository = userRepository;
		this.userImageRepository = userImageRepository;
		this.userUtil = userUtil;
		this.imageUploadService = imageUploadService;
		this.jwtLoginToken = jwtLoginToken;
		this.authRedisService = authRedisService;
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

		if (userRepository.existsByEmail(email)) {
			throw new IllegalAccessError("중복된 아이디입니다.");
		}

		UserEntity userEntity = userRepository.save(new UserEntity(email, username, passwordEncode));

		String createUUID = new CommonUUID().generatorImageUUID();

		userImageRepository.save(new UserImageEntity(userEntity, createUUID, "", "", "DEFAULT"));

		return userEntity;
	}

	/**
	 * 
	 * @param user
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public UserEntity userUpdate(UserDTO userDTO, UserTokenDTO userTokenDTO) {

		UserEntity userEntity = userRepository.findById(userTokenDTO.getId()).get();

		if (userDTO.getIntroComment() != null) {
			userEntity.setIntroComment(userDTO.getIntroComment());
		}
		if (userDTO.getNickName() != null) {

		}
		if (userDTO.getBirth() != null) {

		}

		return userRepository.save(userEntity);
	}

	/**
	 * 
	 * @param userID and Email
	 */

	@Transactional
	public void userDelete(Long userid) {

		userRepository.deleteById(userid);
	}

	/**
	 * 
	 * @param requestUserArg
	 * @throws AuthenticationException
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public String login(UserDTO requestUserArg) throws AuthenticationException {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail())
				.orElseThrow(() -> new NullPointerException("존재하지 않는 아이디 입니다."));

		String userEmail = user.getEmail();
		String password = requestUserArg.getPassword();
		String encPassword = user.getPassword();

		if (!userUtil.isMatch(password, encPassword) && user != null) {
			throw new AuthenticationException("이메일 및 비밀번호가 맞지 않습니다.");
		}

		String accessToken = jwtLoginToken.createAccessToken(user);
		String refreshToken = jwtLoginToken.createRefreshToken(user);

		authRedisService.saveRefreshedLoginUserToken(accessToken, refreshToken);

		return accessToken;
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

	/**
	 * 
	 * @param id
	 * @param requestUserArg
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserEntity updateUserIntroComment(String comment, UserTokenDTO requestUserArg) {

		UserEntity entity = userRepository.findById(requestUserArg.getId()).get();

		entity.setIntroComment(comment);

		return userRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public UserImageEntity updateUserIntroImage(Long userId, MultipartFile userImage) {

		UserImageEntity userIntroImage = userImageService.findUserImageByUserId(userId);

		ImageDTO imageDTO = imageUploadService.saveImageInDir(userImage);

		userIntroImage.setFileName(imageDTO.getFileName());
		userIntroImage.setFilePath(imageDTO.getFilePath());
		userIntroImage.setOriginalFileName(imageDTO.getOriginName());
		userIntroImage.setFileSize(imageDTO.getFileSize());

		// DB 내용 변경
		return userImageService.userImageSave(userIntroImage);
	}

	/**
	 * 
	 * @param userDTO
	 * @throws Exception
	 */

	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUserIntroImage(UserTokenDTO userDTO) throws Exception {

		Long userid = userDTO.getId();

		UserImageEntity userIntroImage = userImageRepository.findUserIntroImageByUserId(userid);
		// 삭제할 이미지가 디폴트 이미지일 경우 리턴

		if (userIntroImage.getFileName().equals("DEFAULT")) {
			return false;
		}

		try {

			imageUploadService.deleteImageInDirectory(userIntroImage.getOriginalFileName(),
					userIntroImage.getFilePath());

			// 디폴트 유저 이미지는 저장되어있기 때문에 DB에만 정보를 저장함
			userImageService.updateDefaultUserIntroImage(userIntroImage);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}

		return true;
	}

//	private boolean deleteUserImageAtStorage(String filePath, String fileName) {
//		try {
//			return imageUploadService.deleteImageInDirectory(filePath, fileName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return false;
//	}
}
