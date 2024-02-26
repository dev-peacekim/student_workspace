package com.blackberry.s20240130103.lhs.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void joinUser(User user) {
		user.setUser_pw(bCryptPasswordEncoder.encode(user.getUser_pw()));
		userRepository.userJoin(user);
	}
	
	@Override
	public Optional<User> findUserById(String id) {
		return userRepository.findUserById(id);
	}
	
	@Override
	public int joinIdChk(String id) {
		Optional<User> userOp = userRepository.findUserById(id);
		if(userOp.isPresent()) {
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	public int loginChk(User user) {
		int result = 0;
		Optional<User> userOp = userRepository.findUserById(user.getUser_id());
		if(userOp.isPresent()) {
			if(bCryptPasswordEncoder.matches(user.getUser_pw(), userOp.get().getUser_pw())) {
				User user2 = userOp.get();
				user.setUser_no(user2.getUser_no());
				user.setUser_profile(user2.getUser_profile());
				user.setUser_name(user2.getUser_name());
				user.setUser_nic(user2.getUser_nic());
				System.out.println("UserServiceImpl 로그인 성공");
				result = 1;
			}else {
				System.out.println("UserServiceImpl 로그인 실패");
				System.out.println(user);
				result = 0;
			}
		}else {
			result = 0;
		}
		return result;
	}
	
	@Override
	public User findUserByNo(String userNo) {
		return userRepository.findUserByNo(userNo);
	}

	@Override
	public void updateUser(User user, String userNo) {
		userRepository.updateUser(user,userNo);
	}
}
