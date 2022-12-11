package com.todo.list.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.repository.mapper.ImageMapper;
import com.todo.list.service.api.ImageApiService;
import com.todo.list.util.auth.UserAuthToken;

@RestController
@RequestMapping("/image/api")
public class ImageApiController {

	@Autowired
	private ImageApiService imageApiService;

	/**
	 * Todo에 등록된 이미지 정보를 가져옴
	 * 
	 * @param Todo unique id
	 * @return Todo Image Path and Image Name
	 */

	@ResponseBody
	@GetMapping("/todo/{id}")
	public ResponseEntity<?> requestTodoImage(@PathVariable Long id) {

		List<ImageMapper> todoImages = imageApiService.findTodoImageByTodoId(id);

		return new ResponseEntity<List<ImageMapper>>(todoImages, HttpStatus.OK);
	}

	/**
	 * User Intro 이미지 정보를 가져옴
	 * 
	 * @param User unique id
	 * @return User Image Path and Image Name
	 */

	@ResponseBody
	@PostMapping("/user")
	public ResponseEntity<?> requestUserIntroImage(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

		ImageMapper userIntroImage = imageApiService.findUserImageByUserId(userTokenDTO.getId());

		return new ResponseEntity<ImageMapper>(userIntroImage, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/source/{filePath}/{fileName}")
	public ResponseEntity<?> requestTodoImageRealResource(@PathVariable String filePath,
			@PathVariable String fileName) {

		Resource resource = imageApiService.todoRealImageResource(filePath, fileName);

		HttpHeaders httpheaders = new HttpHeaders();
		httpheaders.setContentType(MediaType.IMAGE_JPEG);

		return new ResponseEntity<Resource>(resource, httpheaders, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("/user/source/{filePath}/{fileName}")
	public ResponseEntity<?> requestUserImageRealResource(@PathVariable String filePath,
			@PathVariable String fileName) {

		Resource resource = imageApiService.userRealImageResource(filePath, fileName);

		HttpHeaders httpheaders = new HttpHeaders();
		httpheaders.setContentType(MediaType.IMAGE_JPEG);

		return new ResponseEntity<Resource>(resource, httpheaders, HttpStatus.OK);
	}

}
