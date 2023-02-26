package com.todo.list.service.main;

import com.todo.list.entity.TodoImageTempEntity;
import com.todo.list.repository.todo.TodoImageTempRepository;
import com.todo.list.util.uuid.CommonUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TodoImageTempService {

    @Autowired
    private TodoImageTempRepository todoImageTempRepository;

    @Transactional(rollbackFor = Exception.class)
    public void saveTodoTempImage(Long id, MultipartFile multipartFile) {

        TodoImageTempEntity todoImageTempEntity = new TodoImageTempEntity();

        todoImageTempEntity.setId(id);
        todoImageTempEntity.setUuid(new CommonUUID().generateImageUUID());

        todoImageTempRepository.save(new TodoImageTempEntity());
    }

    @Transactional
    public void deleteTodoTempImage() {

    }

    public List<TodoImageTempEntity> findTodoTempImageById(Long id) {

        return todoImageTempRepository.findAllById(id);
    }
}
