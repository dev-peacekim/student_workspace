package com.blackberry.s20240130103.ykm.service;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.ykm.dao.YkmBoardDao;
import com.blackberry.s20240130103.ykm.model.YkmBoardComm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YkmServiceImpl implements YkmService {

	private final YkmBoardDao ykmBoardDao;

	@Override
	public int insertStuPost(YkmBoardComm ykmBoardComm, String string) {
		int result = ykmBoardDao.insertStuPost(ykmBoardComm, string);	
		return result;
	}



}


