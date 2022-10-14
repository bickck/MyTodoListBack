package com.todo.list.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.mapper.QuoteMapper;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

	Page<QuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);

	List<QuoteEntity> findQuoteEntityById(long id);

	List<QuoteEntity> findQuoteEntitiesByIsPublish(Publish publish);

//	@Query(
//			value="SELECT q.id, q.quote, q.author, q.user.username FROM USER_QUOTE_ENTITY q ",
//			//+"inner join q.user u where u.id = q.user",
//			//nativeQuery = true,
//			countQuery = "")
//	QuoteMapper findQuery();
//	List<QuoteEntity> findQuoteEntitiesByUsername(String username);
//	Page<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user, Pageable pageable);
//	Page<UserQuoteEntity> findQuoteEntitiesByUserId(Long id, Pageable pageable);
//	Page<QuoteMapper> findQuoteEntitiesByUser(Long id, Pageable pageable);
//	List<UserQuoteEntity> findQuoteEntitiesByUser(UserEntity user);
//	List<UserQuoteEntity> findQuoteEntitiesByUserId(Long id);
}
