package com.blackberry.s20240130103.ykm.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class YkmBoardDaoImpl implements YkmBoardDao {
	
	private final SqlSession session;

	@Override
	public int insertStuPost(YkmBoardComm ykmBoardComm, String string) {
		int result = session.insert("ykminsertStuPost", ykmBoardComm);
		session.insert("ykmPostFiles", ykmBoardComm);
		return result;
	}



}
