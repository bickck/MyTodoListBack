package com.todo.list.service.user;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.list.controller.builder.QuoteBuilder;
import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.entity.TodoEntity;
import com.todo.list.repository.QuoteRepository;
import com.todo.list.repository.UserRepository;

/**
 * 유저의 Quote 저장 수정 삭제를 제공하는 클래스
 * 
 * @author 3d1935
 *
 */
@Service
public class QuoteService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private QuoteRepository userQuoteRepository;

//	@Autowired
//	private JPAQueryFactory jpaQueryFactory;

	private EntityManager entitiyManager;
	private CriteriaBuilder criteriaBuilder;

	@Autowired
	public QuoteService(EntityManager entityManager) {
		criteriaBuilder = entityManager.getCriteriaBuilder();
		this.entitiyManager = entityManager;
	}

	/**
	 * 
	 * @param quoteDTO
	 * @param userTokenDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public QuoteEntity saveQuote(QuoteDTO quoteDTO, UserTokenDTO userTokenDTO) {
		UserEntity userEntity = repository.findByUsername(userTokenDTO.getUsername());
		Publish publish = Publish.PUBLISH;
		long heart = 0;
		if (quoteDTO.getIsPublish().equals("private")) {
			publish = Publish.PRIVATE;
		}
		return userQuoteRepository
				.save(new QuoteEntity(userEntity, quoteDTO.getQuote(), quoteDTO.getAuthor(), publish, heart));
	}

	/**
	 * 
	 * @param QuoteID
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public QuoteEntity updateQuote(Long id, QuoteDTO quoteDTO, UserTokenDTO tokenDTO) {

		QuoteEntity quoteEntity = userQuoteRepository.findById(id).get();
		Publish publish = null;
		String quote = quoteDTO.getQuote();
		String author = quoteDTO.getAuthor();

		if (quoteDTO.getIsPublish() != null) {
			publish = quoteEntity.getIsPublish();

			if (quoteDTO.getIsPublish().equals("private")) {
				quoteEntity.setIsPublish(publish.PRIVATE);
			} else {
				quoteEntity.setIsPublish(publish.PUBLISH);
			}
		}

		if (quote != null) {
			quoteEntity.setQuote(quoteDTO.getQuote());
		}
		if (author != null) {
			quoteEntity.setAuthor(quoteDTO.getAuthor());
		}

		return userQuoteRepository.save(quoteEntity);
	}

	/**
	 * 
	 * @param QuoteID
	 * 
	 */

	@Transactional
	public void deleteQuote(Long id) {
		userQuoteRepository.deleteById(id);
	}

	/**
	 * 
	 * @param QuoteID
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@Transactional
	public int saveQuoteHeart(Long id) {
		CriteriaUpdate<QuoteEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(QuoteEntity.class);
		Root<QuoteEntity> root = criteriaUpdate.from(QuoteEntity.class);

		criteriaUpdate.set("heart", criteriaBuilder.sum(root.get("heart"), 1));
		criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));

		int result = entitiyManager.createQuery(criteriaUpdate).executeUpdate();

		return result;
	}

	/**
	 * 
	 * @param QuoteID
	 * @param quoteDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	public int updateQuotePublished(Long id, QuoteDTO quoteDTO) {

		CriteriaUpdate<QuoteEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(QuoteEntity.class);
		Root<QuoteEntity> root = criteriaUpdate.from(QuoteEntity.class);

		String currPublish = quoteDTO.getIsPublish();

		if (currPublish.equals(Publish.PUBLISH.toString())) {
			criteriaUpdate.set("isPublish", Publish.PRIVATE);
		}
		criteriaUpdate.set("isPublish", Publish.PUBLISH);
		criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));

		int result = entitiyManager.createQuery(criteriaUpdate).executeUpdate();

		return result;
	}


	/**
	 * criteria query test
	 * 
	 * @param QuoteID
	 * @param quoteDTO
	 * @param userTokenDTO
	 * @return result status 1 : SUCCESS 0 : FAILURE
	 */

	@Transactional
	public int testQuoteUpdate(Long id, QuoteDTO quoteDTO, UserTokenDTO userTokenDTO) {

		CriteriaUpdate<QuoteEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(QuoteEntity.class);
		Root<QuoteEntity> root = criteriaUpdate.from(QuoteEntity.class);

		if (quoteDTO.getIsPublish() != null) {

			if (quoteDTO.getIsPublish().equals("private")) {
				criteriaUpdate.set("ISAVAILABLEPUBLISH", Publish.PRIVATE);
			} else {
				criteriaUpdate.set("ISAVAILABLEPUBLISH", Publish.PUBLISH);
			}
		}

		if (quoteDTO.getQuote() != null) {
			criteriaUpdate.set("QUOTE", quoteDTO.getQuote());
		}

		if (quoteDTO.getAuthor() != null) {
			criteriaUpdate.set("AUTHOR", quoteDTO.getAuthor());
		}

		criteriaUpdate.where(criteriaBuilder.equal(root.get("QUOTE_ID"), quoteDTO.getId()));

		int result = entitiyManager.createQuery(criteriaUpdate).executeUpdate();

		return result;
	}

}
