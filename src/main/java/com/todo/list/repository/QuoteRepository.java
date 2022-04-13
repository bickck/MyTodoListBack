package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.QuetoEntity;

public interface QuoteRepository extends JpaRepository<QuetoEntity, Long> {

}
