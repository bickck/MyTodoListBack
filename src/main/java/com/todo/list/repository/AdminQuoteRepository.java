package com.todo.list.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.base.AdminQuoteEntity;

public interface AdminQuoteRepository extends JpaRepository<AdminQuoteEntity, Long> {

	Page<AdminQuoteEntity> findAll(Pageable pageable);
}
