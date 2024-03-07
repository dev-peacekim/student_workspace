package com.blackberry.s20240130103.lhs.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.lhs.dao.AdminDao;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private final AdminDao adminDao;
	
	@Override
	public Map<String, Long> selectTablesCnt() {
		Map<String, Long> tableCntMap = adminDao.selectTablesCnt();
		return null;
	}
}
