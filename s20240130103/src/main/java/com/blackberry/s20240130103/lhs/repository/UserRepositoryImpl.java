package com.blackberry.s20240130103.lhs.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lhs.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
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
	
	@Override
	public User findUserByNo(String userNo) {
		return entityManager.find(User.class, userNo);
	}
	
	@Override
	public void updateUser(User user, String userNo) {
		User finduser = entityManager.find(User.class, userNo);
		finduser.setUser_name(user.getUser_name());
		finduser.setUser_nic(user.getUser_nic());
		finduser.setUser_phone(user.getUser_phone());
		if(user.getUser_profile()!=null) {
			finduser.setUser_profile(user.getUser_profile());
		}
	}
	
	@Override
	public void updatePasswordUser(String userNo, String newPassword) {
		User finduser = entityManager.find(User.class, userNo);
		finduser.setUser_pw(newPassword);
	}
	
	@Override
	public void deleteUser(String userNo) {
		User finduser = entityManager.find(User.class, userNo);
		finduser.setUser_delete_chk(1);
	}
	
	@Override
	public Optional<User> findIdByemail(String email) {
	    try {
	        String findIdSql = "SELECT u FROM User u WHERE u.user_email = :email and u.user_delete_chk = 0";
	        TypedQuery<User> query = entityManager.createQuery(findIdSql, User.class);
	        query.setParameter("email", email);
	        User user = query.getSingleResult();
	        return Optional.ofNullable(user);
	    } catch (NoResultException e) {
	        // 해당 이메일에 대한 결과가 없을 경우
	        System.out.println("User with email " + email + " not found.");
	        return Optional.empty();
	    } catch (NonUniqueResultException e) {
	        // 해당 이메일에 대한 중복된 결과가 있을 경우
	        System.out.println("Multiple users found with email " + email);
	        return Optional.empty();
	    } catch (Exception e) {
	        // 그 외 예외 처리
	        System.out.println("UserRepositoryImpl findIdByemail exception : " + e.getMessage());
	        return Optional.empty();
	    }
	}
	
	@Override
	public Optional<User> findPassByIdEmail(User user) {
		String findUsersql = "select u from User u where u.user_email = :email and u.user_id = :id and u.user_delete_chk = 0";
		System.out.println("Repository findPassByIdEmail user : " + user);
		try {
			User findUser = (User) entityManager.createQuery(findUsersql).setParameter("email", user.getUser_email()).setParameter("id", user.getUser_id()).getSingleResult();
			System.out.println(findUser);
			return Optional.ofNullable(findUser);
		}catch (NoResultException e) {
			System.out.println("noresultException");
	        return Optional.empty();
	    } catch (NonUniqueResultException e) {
	    	System.out.println("NonUniqueResultException");
	        return Optional.empty();
	    } catch (Exception e) {
	        System.out.println("UserRepositoryImpl findIdByemail exception : " + e.getMessage());
	        return Optional.empty();
	    }
	}

}
