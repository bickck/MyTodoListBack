package com.todo.list.test.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.ServerCloneException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.server.ServerErrorException;

import com.todo.list.DummyData;
import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.controller.builder.BackGroundImgBuilder;
import com.todo.list.controller.builder.page.PageUserBuilder;
import com.todo.list.controller.dto.page.PageUserDTO;
import com.todo.list.controller.dto.service.FileDTO;
import com.todo.list.entity.Publish;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.UserQuoteRepository;
import com.todo.list.repository.UserRepository;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.UserImageUploadService;
import com.todo.list.service.test.UserTest;
import com.todo.list.test.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserQuoteRepository quoteRepository;

	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

	@Autowired
	private UserApiService userApiService;
	
	@Autowired
	private UserTest test;

//	@Autowired
//	private UserImageUploadService imageUploadService;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/test/randomnumberAndPublish")
	public String dummyTest() { 
		DummyData data = new DummyData();
		System.out.println(data.randomNumber());
		System.out.println(data.publish());
		
		return "success";
	}

	@PostMapping
	public String imgUploadTest(@RequestParam(name = "file") MultipartFile multipartFile,
			@RequestParam(name = "fileName") String fileName) {
		UserEntity entity = repository.findByUsername("username0");
		BackGroundImgBuilder mul = new BackGroundImgBuilder().setFileName(fileName).setMultipartFile(multipartFile);

		// imageUploadService.saveImageInDir(mul.builder());
		return "success";
	}

	@GetMapping("/test/insert")
	public String testInsertTime() {
		service.testInsert();
		return "success";
	}
	
	@GetMapping("/test/userTest")
	public void testUser() {
		test.save();
	}

	@GetMapping("/test/select")
	public String testSelectTime() {
		long startTime = System.currentTimeMillis();
		int size = service.testSelect().size();
		long endTime = System.currentTimeMillis();
		System.out.println(size);
		return String.valueOf(endTime - startTime) + "ms";
	}
	
	@GetMapping("/test/jwtProblem")
	public String testJwtProblem() {

		String token = authenticationJwtToken.makeToken(repository.findByUsername("username0"));

		return token;
	}

	@Cacheable(cacheNames = "cacheStorage")
	@GetMapping("/test/queryTest1")
	public String userQuoteQueryTest() {

		long startTime = System.currentTimeMillis();
		int size = quoteRepository.findQuoteEntitiesByIsPublish(Publish.PUBLISH).size();
		long endTime = System.currentTimeMillis();
		System.out.println(size);

		return endTime - startTime + "ms";
	}

	@Cacheable(cacheNames = "cacheStorage")
	@GetMapping("/test/quote/querytest1/{id}")
	public String testQuoteQueryTest(@PathVariable Long id) {
		UserEntity entity = repository.findById((long) 1).get();

		long startTime = System.currentTimeMillis();
//		Iterator<UserQuoteEntity> lists = quoteRepository.findQuoteEntitiesByUser(entity).iterator();
		long endTime = System.currentTimeMillis();

		return endTime - startTime + "ms";
	}

	@GetMapping("/test/quote/querytest2")
	public String testQuoteQueryTest2() {
		UserEntity entity = repository.findById((long) 1).get();

		long startTime = System.currentTimeMillis();
		// Iterator<UserQuoteEntity> lists =
		// quoteRepository.findQuoteEntitiesByUserId(entity.getId()).iterator();
		long endTime = System.currentTimeMillis();

		return endTime - startTime + "ms";
	}

	@GetMapping("/test/make/500error")
	public String makeExceptionError() throws ServerErrorException {

		throw new ServerErrorException("error", new Throwable());

	}

	private static String fileDefaultPath = "E:\\img\\username";

	@GetMapping("/test/file/find1")
	public ResponseEntity<Resource> filefind1() throws IOException {
		File file = new File(fileDefaultPath);
		String contentType = null;
		HttpHeaders headers = new HttpHeaders();
		Resource resource = null;
		Path path = null;

		for (File target : file.listFiles()) {
			File dest = new File(fileDefaultPath + File.separator + target.getName());
			resource = new FileSystemResource(dest);
			contentType = Files
					.probeContentType(Paths.get("E:\\img\\defaultImage" + File.separator + target.getName()));
			System.out.println(resource.getURI().toString());
		}
		headers.add("Content-Type", contentType);

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@GetMapping("/test/file/find2")
	public ResponseEntity<Resource> filefind2() throws IOException {

		String str = fileDefaultPath + File.separator
				+ "25f151db0330e9eb4e08a92df805c9b82b801c82d931a7f5a07463a1267bb767.png";
		String contentType = Files.probeContentType(Paths.get(str));
		HttpHeaders headers = new HttpHeaders();
		Resource resource = new UrlResource(Paths.get(str).toAbsolutePath().normalize().toUri());

		headers.add("Content-Type", contentType);
		logger.info("resource = {} ", resource.exists());

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@GetMapping("/users")
	public PageUserDTO getUsers(@PageableDefault(size = 10, direction = Direction.DESC) Pageable pageable) {

		Page<UserEntity> api = userApiService.getUserList(pageable);
		PageUserBuilder builder = new PageUserBuilder();
		builder.setNumber(api.getNumber());

		builder.setNumberOfElements(api.getNumberOfElements());
		builder.setTotalElements(api.getTotalElements());
		builder.setSize(api.getSize());
		builder.setTotalPages(api.getTotalPages());
		builder.setPageable(api.getPageable());

		// Page<String> apis = api.getContent();

		return null;
	}
}
