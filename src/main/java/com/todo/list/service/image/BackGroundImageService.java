package com.todo.list.service.image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.entity.base.DefaultImageEntity;
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

	public List<DefaultImageEntity> backImageGrounds() {

		return imageRepository.findAll();
	}

}
