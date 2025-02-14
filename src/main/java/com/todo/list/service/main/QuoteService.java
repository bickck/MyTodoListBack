package com.todo.list.service.main;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.QuoteDTO;
import com.todo.list.controller.dto.auth.UserTokenDTO;
import com.todo.list.entity.UserEntity;
import com.todo.list.entity.base.Publish;
import com.todo.list.message.EventMessage;
import com.todo.list.entity.QuoteEntity;
import com.todo.list.repository.quote.QuoteRepository;
import com.todo.list.repository.user.UserRepository;

/**
 * 유저의 Quote 저장 수정 삭제를 제공하는 클래스
 * 
 * @author 3d1935
 *
 */
@Service
public class QuoteService {

	private UserRepository repository;
	private QuoteRepository userQuoteRepository;
	private EntityManager entitiyManager;
	private CriteriaBuilder criteriaBuilder;
	private HeartService heartService;

//	@Autowired
//	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	public QuoteService(EntityManager entityManager, UserRepository repository, QuoteRepository userQuoteRepository,
			HeartService heartService) {
		criteriaBuilder = entityManager.getCriteriaBuilder();
		this.entitiyManager = entityManager;
		this.repository = repository;
		this.userQuoteRepository = userQuoteRepository;
		this.heartService = heartService;
	}

	/**
	 * 
	 * @param quoteDTO
	 * @param userTokenDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */

	@EventMessage(repositoryClass = QuoteRepository.class, message = "Quote를 저장하였습니다.")
	@Transactional(rollbackFor = Exception.class)
	public QuoteEntity saveQuote(QuoteDTO quoteDTO, UserTokenDTO userTokenDTO) {

		QuoteEntity quoteEntity = new QuoteEntity();
		UserEntity userEntity = repository.findById(userTokenDTO.getId()).get();
		Publish defaultPublishValue = Publish.PUBLISH;

		quoteEntity.setUser(userEntity);
		quoteEntity.setAuthor(quoteDTO.getAuthor());
		quoteEntity.setQuote(quoteDTO.getQuote());
		quoteEntity.setIsPublish(defaultPublishValue);
		quoteEntity.setHeart((long) 0);

		if (quoteDTO.getIsPublish().equals("private") || quoteDTO.getIsPublish().equals("PRIVATE")) {
			quoteEntity.setIsPublish(Publish.PRIVATE);
		}

		return userQuoteRepository.save(quoteEntity);
	}

	/**
	 * 
	 * @param QuoteID
	 * @param quoteDTO
	 * @param tokenDTO
	 * @return result status 1 : SUCCESS, 0 : FAILURE or ENTITY INFO
	 */
	@EventMessage(repositoryClass = QuoteRepository.class, message = "Quote를 수정하였습니다.")
	@Transactional(rollbackFor = Exception.class)
	public QuoteEntity updateQuote(Long id, QuoteDTO quoteDTO, UserTokenDTO tokenDTO) {

		QuoteEntity quoteEntity = userQuoteRepository.findById(id).get();
		Publish publish = quoteEntity.getIsPublish();
		String quote = quoteDTO.getQuote();
		String author = quoteDTO.getAuthor();

		if (quoteDTO.getIsPublish().equals("private")) {
			quoteEntity.setIsPublish(publish.PRIVATE);
		} else {
			quoteEntity.setIsPublish(publish.PUBLISH);
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
	@EventMessage(repositoryClass = QuoteRepository.class, message = "Quote를 삭제하였습니다.")
	@Transactional(rollbackFor = Exception.class)
	public void deleteQuote(Long id) {

		heartService.deleteHeartAllByQuoteId(id);
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

		Publish currPublish = quoteDTO.getIsPublish();

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
