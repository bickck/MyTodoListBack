package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.entity.Publish;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.mapper.QuoteMapper;

public interface UserQuoteRepository extends JpaRepository<QuoteEntity, Long> {

//	List<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user);
//
//	List<UserQuoteEntity> findQuoteEntitiesByUserId(Long id);

	Page<QuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);
//	Page<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user, Pageable pageable);
//	Page<UserQuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);
//	Page<QuoteMapper> findQuoteEntitiesByUser(Long id, Pageable pageable);

	List<QuoteEntity> findQuoteEntityById(long id);

	void deleteById(Long id);

	List<QuoteEntity> findQuoteEntitiesByIsPublish(Publish publish);
}
