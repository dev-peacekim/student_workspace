package com.blackberry.s20240130103.lhs.repository;

import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lhs.domain.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	
	private final EntityManager entityManager;
	
	@Override
	public void userJoin(User user) {
		entityManager.persist(user);
	}

}
