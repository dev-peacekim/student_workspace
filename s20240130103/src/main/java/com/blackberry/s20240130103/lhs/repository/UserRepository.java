package com.blackberry.s20240130103.lhs.repository;

import java.util.Optional;

import com.blackberry.s20240130103.lhs.domain.User;

public interface UserRepository {
	void userJoin(User user);

	Optional<User> findUserById(String id);

	User findUserByNo(String userNo);

	void updateUser(User user, String userNo);

	void updatePasswordUser(String userNo, String newPassword);

	void deleteUser(String userNo);

	Optional<User> findIdByemail(String email);

	Optional<User> findPassByIdEmail(User user);

}
