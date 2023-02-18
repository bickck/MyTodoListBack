package com.todo.list.repository.quote;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.base.Publish;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.custom.QuoteApiCustomRepository;
import com.todo.list.repository.mapper.QuoteMapper;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long>, QuoteApiCustomRepository {

	Page<QuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);

	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS createTimestamp, q.heart as heart "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.id = :todoId")
	List<QuoteMapper> findQuoteEntityById(Long todoId);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS createTimestamp, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.user.email = :email "
			+ "AND q.user.id = :userid")
	Page<QuoteMapper> findUserQuoteByUserIdAndUserEmail(Long userid, String email, Pageable pageable);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS createTimestamp, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.user.username = :username")
	Page<QuoteMapper> findUserQuoteByUsername(String username, Pageable pageable);
	
	// api query

	@Query(value = 
			"SELECT q.quote_id AS id, q.quote AS quote, q.author AS author, U.username AS username, q.createTimestamp AS createTimestamp, q.ISAVAILABLEPUBLISH AS isPublish,"
			+ "(SELECT COUNT(sub_q_h.id) FROM QUOTE_HEART_ENTITY AS sub_q_h WHERE q.quote_id = sub_q_h.quote_id) AS heart "
			+ "FROM USER_QUOTE_ENTITY AS q "
			+ "LEFT JOIN USER_ENTITY AS U ON q.USER_ID = U.USER_ID "
			+ "WHERE q.ISAVAILABLEPUBLISH = 'PUBLISH'", nativeQuery = true)
	Page<QuoteMapper> findMainQuotes(Pageable pageable, Publish publish);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS createTimestamp, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.isPublish = :publish")
	Page<QuoteMapper> findDailyQuotes(Pageable pageable, Publish publish);
	
	@Query(value = "SELECT q.id AS id, q.quote AS quote, q.author AS author, q.user.username AS username, q.createTimestamp AS createTimestamp, q.heart AS heart, q.isPublish AS isPublish "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "WHERE q.isPublish = :publish")
	Page<QuoteMapper> findRecommandQuotes(Pageable pageable, Publish publish);
	
}
