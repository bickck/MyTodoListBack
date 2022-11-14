package com.todo.list.repository.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.QuoteHeartEntity;

public interface QuoteHeartRepository extends JpaRepository<QuoteHeartEntity, Long>{

	@Query(value = "",nativeQuery = true)
	Long countQuoteHeartById(Long id);
}
