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
	
	@Override
	public int findUserById(String id) {
		String jpqlSQL = "select count(u) from User u where u.user_id=:id";
		Long result = (Long) entityManager.createQuery(jpqlSQL).setParameter("id", id).getSingleResult();
		System.out.println("UserRepository findUserById cnt : " + result);
		return result.intValue();
	}

}
