package com.todo.list.service.user;

import java.io.IOException;

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
import com.todo.list.repository.UserRepository;
import com.todo.list.repository.image.UserImageRepository;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.image.upload.UserImageUploadService;
import com.todo.list.service.image.user.UserImageService;
import com.todo.list.util.UserUtil;
import com.todo.list.util.uuid.CommonUUID;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 유저의 로그인, 회원가입 유저의 정보 수정등을 제공하는 클래스
 */

@Service
public class UserService {

	private static String defaultUserImagePath = "E:\\img\\defaultImage";
	private static String defaultUserImageName = "blank-profile-picture-gdf6b93f73_640.png";

	private UserImageService userImageService;
	private UserRepository userRepository;
	private UserImageRepository userImageRepository;
	private ImageUploadService imageUploadService = new UserImageUploadService();
	private UserUtil userUtil;

	@Autowired
	public UserService(UserImageService userImageService, UserRepository userRepository,
			UserImageRepository userImageRepository, UserUtil userUtil) {
		// TODO Auto-generated constructor stub
		this.userImageService = userImageService;
		this.userRepository = userRepository;
		this.userImageRepository = userImageRepository;
		this.userUtil = userUtil;
	}

	/**
	 * 
	 * @param userDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public UserEntity userSave(UserDTO userDTO) {
		String email = userDTO.getEmail();
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();
		String passwordEncode = userUtil.bCrypt(password);

		if (userRepository.existsByEmail(email)) {
			throw new IllegalAccessError("중복된 아이디입니다.");
		}

		UserEntity userEntity = userRepository.save(new UserEntity(email, username, passwordEncode));
		userImageRepository.save(new UserImageEntity(userEntity, defaultUserImageName, defaultUserImagePath));

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

	// 이메일 확인 및 비밀번호 확인 로직 필요

	/**
	 * 
	 * @param requestUserArg
	 * @throws AuthenticationException
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

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

	/**
	 * 
	 * @param id
	 * @param requestUserArg
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */
	@Transactional
	public UserEntity changeUserPassword(Long id, UserDTO requestUserArg) {

		UserEntity user = userRepository.findByEmail(requestUserArg.getEmail());

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
	public UserImageEntity updateUserIntroImage(Long id, MultipartFile userImage) {

		UserImageEntity userIntroImage = userImageService.findById(id);

		// 디폴트 이미지일 경우 DB 내용은 삭제하되 디스크에 저장된 이미지는 삭제되면 안됌
		if (!userIntroImage.getFileName().equals(defaultUserImageName)
				&& !userIntroImage.getFilePath().equals(defaultUserImagePath)) {
			deleteUserImageAtStorage(userIntroImage.getFilePath(), userIntroImage.getFilePath());
			userImageService.deleteUserIntroImage(id);
		}
		
		ImageDTO imageDTO = imageUploadService.saveImageInDir(userImage);

		UserImageEntity userImageEntity = new UserImageEntity(userIntroImage.getUser(), imageDTO);

		return userImageService.userImageSave(userImageEntity);
	}
	
	/**
	 * 
	 * @param userDTO
	 * @throws Exception
	 */

	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUserIntroImage(UserTokenDTO userDTO) throws Exception {

		Long userid = userDTO.getId();

		UserEntity user = userRepository.findById(userid).get();

		UserImageEntity userIntroImage = userImageRepository.findUserIntroImageByUserId(userid);
		// 삭제할 이미지가 디폴트 이미지일 경우 리턴
		
		if (userIntroImage.getFileName().equals(defaultUserImageName)
				&& userIntroImage.getFilePath().equals(defaultUserImagePath)) {
			return false;
		}

		try {
			
			imageUploadService.deleteImageInDirectory(userIntroImage.getOriginalFileName(), userIntroImage.getFilePath());
			
			// 디폴트 유저 이미지는 저장되어있기 때문에 DB에만 정보를 저장함
			userImageService.updateDefaultUserIntroImage(userIntroImage, defaultUserImageName, defaultUserImagePath);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}

		return true;
	}

	private boolean deleteUserImageAtStorage(String filePath, String fileName) {
		try {
			return imageUploadService.deleteImageInDirectory(filePath, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
