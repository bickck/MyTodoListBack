package com.todo.list.repository.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.list.entity.QuoteHeartEntity;

public interface QuoteHeartRepository extends JpaRepository<QuoteHeartEntity, Long>{

	//@Query(value = "",nativeQuery = true)
	//Long countQuoteHeartById(Long id);
	
	@Modifying
	@Query(value = "DELETE FROM QUOTE_HEART_ENTITY "
			+ "WHERE QUOTE_ID = :quoteid",nativeQuery = true)
	void deleteAllByQuoteId(@Param(value = "quoteid")Long quoteid);
	
	void deleteByUuid(String uuid);
	
	boolean existsByQuoteEntityIdAndUserId(Long quoteid, Long userid);
	
	@Query(value = "SELECT q.QUOTE_UUID "
			+ "FROM QUOTE_HEART_ENTITY AS q WHERE q.QUOTE_ID = :quoteId AND q.USER_ID = :userId"
			, nativeQuery = true)
	String findQuoteUUID(Long quoteId, Long userId);
}
