package com.blackberry.s20240130103.lhs.repository;

import java.util.Optional;

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
	public Optional<User> findUserById(String id) {
		//jpql count 사용법
		//String jpqlSQL = "select count(u) from User u where u.user_id=:id";
		//Long result = (Long) entityManager.createQuery(jpqlSQL).setParameter("id", id).getSingleResult();
		String findUserIdSql = "select u from User u where u.user_id=:id";
		User user = null;
		try {
			user =  (User) entityManager.createQuery(findUserIdSql).setParameter("id", id).getSingleResult();
		}catch (Exception e) {
			System.out.println("UserRepositoryImpl findUserById exception : " + e.getMessage());
		}
		return Optional.ofNullable(user);
	}

}
