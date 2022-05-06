package com.todo.list.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.repository.DefaultImageRepository;

@Service
public class BackGroundImageService {


	@Autowired
	private DefaultImageRepository imageRepository;
	
	public String save(MultipartFile multipartFile) {
		imageRepository.save(null);
		return null;
	}
	
	public void delete(Long id) {
		
		imageRepository.deleteById(id);
	}
	
}
