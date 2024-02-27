package com.blackberry.s20240130103.lsl.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LslDaoImpl implements LslDao {

	private final SqlSession session;

	@Override
	public int freeBoardWrite(LslBoardComm lslBoardComm) {
		int insertResult = 0;
		System.out.println("LslDaoImpl freeBoardWrite Start...");
		try {
			insertResult = session.insert("slFreeBoardWrite", lslBoardComm);
		} catch (Exception e) {
			System.out.println("LslDaoImpl freeBoardWrite ->" + e.getMessage());
		}
		return insertResult;
	}

	

}
