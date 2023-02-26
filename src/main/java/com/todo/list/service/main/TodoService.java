package com.todo.list.service.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import com.todo.list.entity.*;
import com.todo.list.service.image.physical.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.base.Publish;
import com.todo.list.message.EventMessage;
import com.todo.list.repository.todo.TodoRepository;
import com.todo.list.repository.user.UserRepository;
import com.todo.list.service.image.logical.TodoImageService;

/**
 * 해당 유저의 Todo 데이터를 저장,수정,삭제를 제공하는 클래스
 */

@Service
public class TodoService {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private UserRepository userRepository;
    private TodoRepository todoRepository;
    private HeartService heartService;

    @Autowired
    private TodoImageTempService todoImageTempService;


    @Autowired
    private ImageUploader imageUploader;

    @Autowired
    private TodoImageService todoImageService;

    @Autowired
    public TodoService(EntityManager entityManager, UserRepository userRepository, HeartService heartService,
                       TodoRepository todoRepository) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.userRepository = userRepository;
        this.heartService = heartService;
        this.todoRepository = todoRepository;
    }

    /**
     * @param userTokenDTO : User auth token
     * @param todoDTO
     * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO or Null
     */

    @EventMessage(repositoryClass = TodoRepository.class, message = "Todo를 저장하였습니다.")
    @Transactional(rollbackFor = Exception.class)
    public TodoEntity saveTodo(UserTokenDTO userTokenDTO, TodoDTO todoDTO) {
        UserEntity user = userRepository.findById(userTokenDTO.getId()).get();

        TodoEntity entity = todoRepository
                .save(new TodoEntity(user, todoDTO.getTitle(), todoDTO.getContent(), todoDTO.getIsPublish()));

        return saveTodoImagesChain(entity);
    }

    /**
     * @param id      : Todo Identify
     * @param todoDTO
     * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
     * @throws Exception
     */

    @EventMessage(repositoryClass = TodoRepository.class, message = "Todo를 수정하였습니다.")
    @Transactional(rollbackFor = Exception.class)
    public TodoEntity updateTodo(Long id, UserTokenDTO userTokenDTO, TodoDTO todoDTO) throws Exception {
        TodoEntity entity = todoRepository.findTodoEntityById(id);

        String title = todoDTO.getTitle();
        String content = todoDTO.getContent();

        if (todoDTO.getIsPublish().equals("private") || todoDTO.getIsPublish().equals("PRIVATE")) {
            entity.setIsPublish(Publish.PRIVATE);
        }

        if (title != null) {
            entity.setTitle(title);
        }

        if (content != null) {
            entity.setContent(content);
        }

        // 새롭게 Todo Image를 추가하고자 한다면




        return updateTodoImagesChain(todoRepository.save(entity));
    }

    /**
     * @param id
     * @throws Exception
     */

    @EventMessage(repositoryClass = TodoRepository.class, message = "Todo를 삭제하였습니다.")
    @Transactional(rollbackFor = Exception.class)
    public void deleteTodo(Long id) throws Exception {
        TodoEntity entity = todoRepository.findById(id).get();

//        boolean isFileDelete = deleteFiles(entity);
//
//        if (isFileDelete) {
//
//            heartService.deleteHeartAllByTodoId(entity.getId());
//            deleteFiles(entity);
//            todoRepository.deleteById(id);
//        }
    }

    /**
     * @param id
     * @param username
     * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
     */

    @Transactional
    public void updateTodoPublished(Long id, String username) {
        TodoEntity entity = todoRepository.findById(id).get();

        if (entity.getIsPublish().equals(Publish.PRIVATE)) {
            entity.setIsPublish(Publish.PUBLISH);
        } else {
            entity.setIsPublish(Publish.PRIVATE);
        }

        todoRepository.save(entity);

    }
    /**
     * @param todoEntity : todo Identify
     */

    private TodoEntity saveTodoImagesChain(TodoEntity todoEntity) {

        Long id = todoEntity.getId();

        List<TodoImageTempEntity> lists = todoImageTempService.findTodoTempImageById(id);

        if (lists.isEmpty()) {
            return todoEntity;
        }

        for (TodoImageTempEntity todoImageTempEntity : lists) {
            todoImageService.saveTodoImage(id, todoImageTempEntity);
        }

        return todoEntity;
    }

    /**
     * @param todoEntity : Todo Identity
     */

    private TodoEntity updateTodoImagesChain(TodoEntity todoEntity) {

        Long id = todoEntity.getId();

        List<TodoImageTempEntity> lists = todoImageTempService.findTodoTempImageById(id);

        if (lists.isEmpty()) {
            return todoEntity;
        }

        for (TodoImageTempEntity todoImageTempEntity : lists) {
            todoImageService.updateTodoImage(id ,todoImageTempEntity);
        }

        return todoEntity;
    }
}
