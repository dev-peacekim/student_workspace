package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.lhs.dao.AdminDao;
import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.User;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private final AdminDao adminDao;
	
	@Override
	public Map<String, Long> selectTablesCnt() {
		Map<String, Long> tableCntMap = adminDao.selectTablesCnt();
		return tableCntMap;
	}
	
	@Override
	public List<Map<String, Long>> selectUserJoinCnt() {
		List<Map<String, Long>> userJoinCntList = adminDao.selectUserJoinCnt();
		return userJoinCntList;
	}
	
	@Override
	public List<User> selectUsersDeleteRequest() {
		List<User> deleteUserList = adminDao.selectUsersDeleteRequest();
		return deleteUserList;
	}
	
	@Override
	public List<BoardAdmin> selectBoardAdminList() {
		List<BoardAdmin> boardAdminList = adminDao.selectBoardAdminList();
		return boardAdminList;
	}
}
