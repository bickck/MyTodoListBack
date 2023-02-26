package com.todo.list.controller.main;


import com.todo.list.controller.response.message.ResponseMessageEntity;
import com.todo.list.service.image.logical.TodoImageService;
import com.todo.list.service.main.TodoImageTempService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/user/todo/image", headers = HttpHeaders.AUTHORIZATION)
public class TodoImageController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TodoImageTempService todoImageTempService;

    @Autowired
    private TodoImageService todoImageService;


    @PostMapping(value = "/temp/{id}")
    public ResponseMessageEntity<?> requestSaveTempTodoImage(@PathVariable Long id, MultipartFile multipartFile) {

        todoImageTempService.saveTodoTempImage(id, multipartFile);


        return new ResponseMessageEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseMessageEntity<?> requestSaveTodoImage(@PathVariable Long id, MultipartFile multipartFile) {


//		todoImageService.todoImageSave();


        return new ResponseMessageEntity<>("SUCCESS", HttpStatus.OK);
    }
}
