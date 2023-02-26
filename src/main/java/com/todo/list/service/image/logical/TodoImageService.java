package com.todo.list.service.image.logical;

import java.util.List;

import com.todo.list.entity.TodoImageTempEntity;
import com.todo.list.repository.todo.TodoRepository;
import com.todo.list.service.image.physical.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.entity.TodoEntity;
import com.todo.list.entity.TodoImageEntity;
import com.todo.list.repository.image.TodoImageRepository;

@Service
public class TodoImageService {


    @Autowired
    private ImageUploader imageUploader;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoImageRepository todoImageRepository;

    /**
     * @param id              : Todo Identify
     * @param todoImageTempEntity : Todo image info in repository
     * @return
     */

    @Transactional
    public TodoImageEntity saveTodoImage(Long id, TodoImageTempEntity todoImageTempEntity) {

        TodoEntity todoEntity = todoRepository.findById(id).get();

        String originalFileName = todoImageTempEntity.getOriginalFileName();
        Long fileSize = todoImageTempEntity.getFileSize();
        String imageRealPath = todoImageTempEntity.getImageRealPath();
        String imageLogicPath = todoImageTempEntity.getImageLogicPath();

        return todoImageRepository.save(new TodoImageEntity(todoEntity, imageRealPath, originalFileName, imageLogicPath, fileSize));
    }

    /**
     * @param id : Todo Identify
     * @param todoImageTempEntity   : Todo Image Info
     * @return
     */

    @Transactional
    public TodoImageEntity updateTodoImage(Long id, TodoImageTempEntity todoImageTempEntity) {

        TodoEntity todoEntity = todoRepository.findById(id).get();

        List<TodoImageEntity> todoImageEntity = todoImageListByTodoId(todoEntity);

        String originalFileName = todoImageTempEntity.getOriginalFileName();
        Long fileSize = todoImageTempEntity.getFileSize();
        String imageRealPath = todoImageTempEntity.getImageRealPath();
        String imageLogicPath = todoImageTempEntity.getImageLogicPath();

        return todoImageRepository.save(new TodoImageEntity(todoEntity, imageRealPath, originalFileName, imageLogicPath, fileSize));
    }

    /**
     * @param todoImageEntity
     */

    public void todoImageDelete(TodoImageEntity todoImageEntity) {

        todoImageRepository.delete(todoImageEntity);
    }

    /**
     * @param id
     * @return
     */

    @Transactional(readOnly = true)
    public List<TodoImageEntity> todoImageListByTodoId(TodoEntity id) {
        return todoImageRepository.findTodoImageEntityByTodoBoard(id);
    }
}
