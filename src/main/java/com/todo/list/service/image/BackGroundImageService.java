package com.todo.list.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.repository.ImageRepository;

@Service
public class BackGroundImageService {


	@Autowired
	private ImageRepository imageRepository;
	
	public String save(MultipartFile multipartFile) {
		return null;
		
	}
	
}
