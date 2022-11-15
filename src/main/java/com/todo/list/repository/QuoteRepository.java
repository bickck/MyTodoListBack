package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.base.Publish;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.mapper.QuoteMapper;
import com.todo.list.repository.quote.QuoteApiRepository;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long>, QuoteApiRepository {

	Page<QuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);

	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS CREATETIMESTAMP, q.heart as heart "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.id = :todoId")
	List<QuoteMapper> findQuoteEntityById(Long todoId);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS CREATETIMESTAMP, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.user.email = :email "
			+ "AND q.user.id = :userid")
	Page<QuoteMapper> findUserQuoteByUserIdAndUserEmail(Long userid, String email, Pageable pageable);
	
	// api query

	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS CREATETIMESTAMP, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.isPublish = :publish")
	Page<QuoteMapper> findMainQuotes(Pageable pageable, Publish publish);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS CREATETIMESTAMP, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.isPublish = :publish")
	Page<QuoteMapper> findDailyQuotes(Pageable pageable, Publish publish);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS CREATETIMESTAMP, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.isPublish = :publish")
	Page<QuoteMapper> findRecommandQuotes(Pageable pageable, Publish publish);
	

//	List<QuoteEntity> findQuoteEntitiesByUsername(String username);
//	Page<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user, Pageable pageable);
//	Page<UserQuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);
//	Page<QuoteMapper> findQuoteEntitiesByUser(Long id, Pageable pageable);
//	List<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user);
//	List<UserQuoteEntity> findQuoteEntitiesByUserId(Long id);
}
