package com.todo.list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.domain.UserQuoteEntity;

public interface UserQuoteRepository extends JpaRepository<UserQuoteEntity, Long> {

	//List<UserQuoteEntity> findByUsername(String username);
	
	void deleteById(Long id);
}
