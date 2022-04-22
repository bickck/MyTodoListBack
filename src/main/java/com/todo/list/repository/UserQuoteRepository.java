package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.UserQuoteEntity;

public interface UserQuoteRepository extends JpaRepository<UserQuoteEntity, Long> {

}
