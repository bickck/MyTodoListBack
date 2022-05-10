package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.base.DefaultQuoteEntity;

public interface DefaultQuoteRepository extends JpaRepository<DefaultQuoteEntity, Long> {

}
