package com.blackberry.s20240130103.lhs.repository;

import com.blackberry.s20240130103.lhs.domain.User;

public interface UserRepository {
	void userJoin(User user);

	int findUserById(String id);
}
