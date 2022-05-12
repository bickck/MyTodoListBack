package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.UserQuoteEntity;

public interface UserQuoteRepository extends JpaRepository<UserQuoteEntity, Long> {

	//List<UserQuoteEntity> findQuoteEntitiesByUsername(String username, Pageable pageable);

	void deleteById(Long id);
}
