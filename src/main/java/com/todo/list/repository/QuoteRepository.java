package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.base.Publish;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.mapper.QuoteMapper;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

	Page<QuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);

	@Query(value = "SELECT q.id AS id, q.quote as quote, q.author as author, q.user.username as username, q.createTimestamp as createTimestamp, q.heart as heart "
			+ "FROM USER_QUOTE_ENTITY q WHERE q.id = :id")
	List<QuoteMapper> findQuoteEntityById(Long id);

	@Query(value = "SELECT q.id AS id, q.quote as quote, q.author as author, q.user.username as username, q.createTimestamp as createTimestamp, q.heart as heart "
			+ "FROM USER_QUOTE_ENTITY q WHERE q.isPublish = :publish")
	Page<QuoteMapper> findMainQuotes(Pageable pageable, Publish publish);

//	List<QuoteEntity> findQuoteEntitiesByUsername(String username);
//	Page<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user, Pageable pageable);
//	Page<UserQuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);
//	Page<QuoteMapper> findQuoteEntitiesByUser(Long id, Pageable pageable);
//	List<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user);
//	List<UserQuoteEntity> findQuoteEntitiesByUserId(Long id);
}
