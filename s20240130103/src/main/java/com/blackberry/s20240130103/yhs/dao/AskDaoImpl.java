package com.blackberry.s20240130103.yhs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.yhs.model.Ask;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class AskDaoImpl implements AskDao {
	
	private final EntityManager em;
	
	@Override
	public Ask save(Ask ask) {
		System.out.println("AskRepositoryImpl save start...");
		em.persist(ask);
		return ask;
	}

	@Override
	public List<Ask> findAll() {
		System.out.println("AskDaoImpl findAll start...");
		List<Ask> memberList = em.createQuery("select m from BOARD_ADMIN m", Ask.class)
									.getResultList();
		return memberList;
	}

	@Override
	public Optional<Ask> findById(Long AskId) {
		Ask ask = em.find(Ask.class, AskId);
		return Optional.ofNullable(ask);
	}

	@Override
	public void updateByAsk(Ask ask) {
		System.out.println("AskRepositoryImpl updateByMember admin->" +ask);
		//merge	-->현재 Setting 된것만 수정, 나머지는 Null
		//em.merge(member);
		Ask Admin3 = em.find(Ask.class, ask.getUser_no());
		Admin3.setUser_no(ask.getUser_no());
		Admin3.setCol(ask.getCol());
		em.merge(ask);
		return;
	}

}
