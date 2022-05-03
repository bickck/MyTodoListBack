package com.todo.list.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.UserDTO;
import com.todo.list.controller.dto.UserTokenDTO;
import com.todo.list.domain.UserEntity;
import com.todo.list.domain.UserQuoteEntity;
import com.todo.list.domain.base.DefaultQuoteEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.security.AuthenticationJwtToken;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.ImageUploadService;
import com.todo.list.service.queto.DefaultQuetoService;
import com.todo.list.service.queto.UserQuoteService;
import com.todo.list.service.user.UserService;
import com.todo.list.util.aop.TokenValidator;

import io.jsonwebtoken.Claims;

@RestController
public class TestController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserApiService userApiService;

	@Autowired
	private AuthenticationJwtToken jwtLoginToken;

	@Autowired
	private UserQuoteService userQuoteService;

	@Autowired
	private DefaultQuetoService defaultQuetoService;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private ImageUploadService imageUploadService;

	private UserTokenDTO dto;
	private UserDTO dto2;
	private QuoteDTO dto3;

	@Value("${spring.servlet.multipart.max-file-size}")
	private String fileMaxSize;

	@PostConstruct
	public void initSetting() {
		dto = new UserTokenDTO((long) 1, "1234");
		dto2 = new UserDTO("1234", "134");
		dto3 = new QuoteDTO("quoteTest", "quoteTest");
		UserEntity userEntity = userApiService.getUserApi(dto);

	}

	@PostMapping("/create/token")
	public String createToken() {
		String s = jwtLoginToken.createTokenTest(new UserTokenDTO((long) 1234, "username"));
		return s;

	}

	@PostMapping("/img/test")
	public String imgTest(@RequestParam(name = "file") MultipartFile multipartFile,
			@TokenValidator UserTokenDTO userTokenDTO) throws Exception {

		System.out.println("OriginalFileName : " + multipartFile.getOriginalFilename());
		System.out.println("FileName : " + multipartFile.getName());
//		Path path = Paths.get("E:\\img");

//		try {
//			InputStream inputStream = multipartFile.getInputStream();
//			Path filePath = path.resolve(multipartFile.getOriginalFilename());
//			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		return "success";
	}

	@PostMapping("/img/save/test")
	public String imgSaveTest(@RequestParam(name = "file") MultipartFile multipartFile,
			@TokenValidator UserTokenDTO userTokenDTO) throws Exception {

		imageUploadService.saveImageInDir(multipartFile, "username", "yadong");

		return "success";
	}

	@PostMapping("/create/test")
	public void aopTest(@RequestBody UserDTO reqBody, @TokenValidator UserTokenDTO tokenUser) {
		System.out.println("Test");
		System.out.println("token User : " + tokenUser.toString());
	}

	@GetMapping("/value/test")
	public void valueTest() {
		String username = "1234";
		Path path = Paths.get("E:\\img\\" + File.separator + username);

		if (!Files.isExecutable(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("isExist : " + Files.isExecutable(path));
		}
	}

	@PostMapping(value = "/quote/2")
	public List<UserQuoteEntity> quoteSaveTest() {
		UserEntity userEntity = userApiService.getUserApi(dto);
		List<UserQuoteEntity> entities = new ArrayList<UserQuoteEntity>();
		entities.add(new UserQuoteEntity(userEntity.getUsername(), dto3));
		userEntity.setQuotes(entities);
		repository.saveAndFlush(userEntity);
		return userEntity.getQuotes();
	}

	@PostMapping(value = "/quote/1")
	public List<UserQuoteEntity> quoteSelectQuote() {
		return userApiService.getUserquotes(dto);
	}

	@PostMapping(value = "/quote/3/{id}")
	public String deleteQuote(@PathVariable int id) {

		return "success";
	}

	@PostMapping(value = "/quote/test")
	public ResponseEntity<List<DefaultQuoteEntity>> requestTest() {
		List<DefaultQuoteEntity> quotes = defaultQuetoService.getQuotes();
		return new ResponseEntity<List<DefaultQuoteEntity>>(quotes, HttpStatus.OK);
	}

	@PostMapping("/get/valid")
	public Claims getValid(HttpServletRequest httpServletRequest) {

		Enumeration<String> s = httpServletRequest.getHeaderNames();
		String authorization = httpServletRequest.getHeader("authorization");

		Claims body = jwtLoginToken.getUser(authorization);

		return body;
	}

}
