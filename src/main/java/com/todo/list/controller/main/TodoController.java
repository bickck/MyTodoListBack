package com.todo.list.controller.main;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.todo.list.controller.ResponseStatusMessage;
import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;

import com.todo.list.entity.base.Publish;

import com.todo.list.service.user.TodoService;

import com.todo.list.util.auth.UserAuthToken;

/**
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 */

@RestController
@RequestMapping(value = "/user/todo", headers = HttpHeaders.AUTHORIZATION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private TodoService userTodoService;

    @Autowired
    public TodoController(TodoService userTodoService) {
        this.userTodoService = userTodoService;
    }

    /**
     * @param todoDTO
     * @param userTokenDTO
     * @return status
     * @throws IOException
     */

    @PostMapping(value = "/post")
    public ResponseEntity<?> requestSaveUserTodoForMultiPartForm(
            @RequestBody TodoDTO todoDTO, @UserAuthToken UserTokenDTO userTokenDTO) throws IOException {


        userTodoService.saveTodo(userTokenDTO, todoDTO);

        return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
    }

    /**
     * @param id -> Post Identify
     * @param todoDTO
     * @param userTokenDTO
     * @return status
     * @throws Exception
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    @PutMapping(value = "/post/{id}")
    public ResponseEntity<?> requestUpdateUserTodoForMultipart(
            @PathVariable(value = "id") Long id, @RequestBody TodoDTO todoDTO, @UserAuthToken UserTokenDTO userTokenDTO) throws Exception {

        userTodoService.updateTodo(id, todoDTO);

        return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
    }

    /**
     *
     * @param id -> Post Identify
     * @param userTokenDTO -> userToken
     * @return response message
     */

    @PutMapping(value = "/publish/{id}")
    public ResponseEntity<?> updateTodoPublished(@PathVariable(value = "id") Long id, @UserAuthToken UserTokenDTO userTokenDTO) {

        userTodoService.updateTodoPublished(id, userTokenDTO.getUsername());

        return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);

    }

    /**
     * @param id
     * @param userTokenDTO
     * @return status
     * @throws Exception
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> requestDeleteUserTodo(@PathVariable Long id, @UserAuthToken UserTokenDTO userTokenDTO)
            throws Exception {

        try {
            userTodoService.deleteTodo(id);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<String>(ResponseStatusMessage.SUCCESS, HttpStatus.OK);
    }

}
