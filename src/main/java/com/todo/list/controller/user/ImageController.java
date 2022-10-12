package com.todo.list.controller.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.builder.BackGroundImgBuilder;
import com.todo.list.controller.dto.ImageInfoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.ImageService;
import com.todo.list.service.image.UserImageUploadService;
import com.todo.list.service.image.admin.MainBackGroundImageService;
import com.todo.list.service.image.user.UserImageService;
import com.todo.list.service.queto.DefaultQuetoService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private UserImageUploadService userImageUploadService;
	
	@Autowired
	private UserImageService userBackGroundImgService;

	@Autowired
	private ImageService imaegService;

	@Autowired
	public ImageController(UserImageUploadService userImageUploadService) {

		this.imaegService = userImageUploadService;

	}

	/*
	 * BackGroundImg CRUD
	 */

	@PostMapping("/save/userImage")
	public ResponseEntity<?> saveUserIntroImage(@RequestParam(name = "file") MultipartFile multipartFile,
			@RequestParam(name = "fileName") String fileName, @UserAuthToken UserTokenDTO tokenDTO) throws IOException {

		BackGroundImgBuilder backGroundImgBuilder = new BackGroundImgBuilder();
		backGroundImgBuilder.setFileName(fileName).setMultipartFile(multipartFile).setUserName(tokenDTO.getUsername());
		imaegService.saveImageInDir(backGroundImgBuilder.builder());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/background/2/{id}")
	public ResponseEntity<?> updateUserIntroImage(@PathVariable Long id) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/background/3/{id}")
	public ResponseEntity<?> deleteUserIntroImage(@PathVariable Long id) {
		// imaegService.deleteBackGroundImage(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@ResponseBody
//	@GetMapping("/api/img")
//	public ResponseEntity<Resource> responseBackGroundsImageList(@RequestParam(value = "filename") String fileName)
//			throws FileNotFoundException {
//
//		long startTime = System.currentTimeMillis();
//		Resource resource = backGroundImageService.getResource(fileName);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_JPEG);
//		long endTime = System.currentTimeMillis();
//
//		logger.info("responseBackGroundsImageList take Time : {}ms", endTime - startTime);
//		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
//	}
}
