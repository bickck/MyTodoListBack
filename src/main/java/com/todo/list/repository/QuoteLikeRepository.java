package com.todo.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.QuoteEntity;

public interface QuoteLikeRepository extends JpaRepository<QuoteEntity, Long>{

}
