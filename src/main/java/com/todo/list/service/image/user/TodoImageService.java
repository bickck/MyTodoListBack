package com.todo.list.service.image.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.ImageDTO;
import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoImageEntity;
import com.todo.list.repository.TodoImageRepository;

@Service
public class TodoImageService {

	@Autowired
	private TodoImageRepository todoImageRepository;

	@Transactional
	public TodoImageEntity todoImageSave(TodoEntity todoEntity, ImageDTO imageDTO) {

		TodoImageEntity todoImage = new TodoImageEntity(todoEntity, imageDTO.getFileName(), imageDTO.getOriginName(),
				imageDTO.getFilePath(), imageDTO.getFileSize());

		return todoImageRepository.save(todoImage);
	}

	public void todoImageDelete() {

	}

	public void todoImageUpdate() {

	}
}
