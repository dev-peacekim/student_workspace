package com.blackberry.s20240130103.lhs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.lhs.domain.User;
import com.blackberry.s20240130103.lhs.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public void joinUser(User user) {
		userRepository.userJoin(user);
	}

}
