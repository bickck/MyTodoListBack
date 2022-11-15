package com.todo.list.repository.quote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.mapper.QuoteMapper;

public interface QuoteLikeRepository extends JpaRepository<QuoteEntity, Long>{

	@Query(value = 
			"SELECT q.quote_id AS id, q.quote AS quote, q.author AS author, u.username AS username, q.createTimestamp AS CREATETIMESTAMP, q.isavailablepublish as isPublish, "
			+ "(SELECT COUNT(sub_q_h.id) FROM QUOTE_HEART_ENTITY AS sub_q_h WHERE q.quote_id = sub_q_h.id) AS HEART "
			+ "FROM USER_QUOTE_ENTITY q "
			+ "INNER JOIN QUOTE_HEART_ENTITY AS quote_heart "
			+ "ON quote_heart.quote_id = q.quote_id "
			+ "INNER JOIN USER_ENTITY AS u "
			+ "ON quote_heart.user_id = u.user_id "
			+ "WHERE q.user_id = :id "
			+ "AND u.email = :email ", nativeQuery = true)
	Page<QuoteMapper> findUserLikeQuoteByUserIdAndEmail(Long id, String email, Pageable pageable);
}

/**
 * 유저가 좋아요를 누른 Quote를 가져오려면 먼저 Quote Heart Entity 안에 있는 Quote id들을 가져와야하고 유저 quote heart entity 안에 있는 user id도 같아야함
 * 
 * */