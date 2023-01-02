package com.todo.list.repository.event;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.MessageEntity;

public interface EventMessageRepository extends JpaRepository<MessageEntity, UUID> {

}
