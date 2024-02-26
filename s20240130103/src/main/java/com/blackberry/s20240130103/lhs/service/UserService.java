package com.blackberry.s20240130103.lhs.service;

import java.util.Optional;

import com.blackberry.s20240130103.lhs.domain.User;

public interface UserService {
	void joinUser(User user);

	Optional<User> findUserById(String id);

	int joinIdChk(String id);

	int loginChk(User user);

	User findUserByNo(String userNo);

	void updateUser(User user, String userNo);
}
