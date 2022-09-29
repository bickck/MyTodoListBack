package com.todo.list.controller.user;

import java.io.FileNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.controller.dto.ImageInfoDTO;
import com.todo.list.entity.base.AdminImageEntity;
import com.todo.list.service.api.UserApiService;
import com.todo.list.service.image.admin.MainBackGroundImageService;
import com.todo.list.service.queto.DefaultQuetoService;

@RestController
public class ImageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private MainBackGroundImageService backGroundImageService;

	@Autowired
	public ImageController(MainBackGroundImageService backGroundImageService) {

		this.backGroundImageService = backGroundImageService;

	}

	@ResponseBody
	@GetMapping("/api/img")
	public ResponseEntity<Resource> responseBackGroundsImageList(@RequestParam(value = "filename") String fileName)
			throws FileNotFoundException {

		long startTime = System.currentTimeMillis();
		Resource resource = backGroundImageService.getResource(fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		long endTime = System.currentTimeMillis();

		logger.info("responseBackGroundsImageList take Time : {}ms", endTime - startTime);
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/api/img/infos")
	public List<ImageInfoDTO> responseBackGroundImageIds() {

		List<ImageInfoDTO> list = new ArrayList<ImageInfoDTO>();
		Iterator<AdminImageEntity> adminImageEntities = backGroundImageService.imageNames().iterator();
		while (adminImageEntities.hasNext()) {
			list.add(new ImageInfoDTO(adminImageEntities.next()));
		}
		return list;
	}
}
