package com.blackberry.s20240130103.lsl.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslBoardCommFile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LslDaoImpl implements LslDao {
	
	private final SqlSession session;

	@Override
	public List<LslBoardComm> boardFreeList(LslBoardComm boardComm) {
		List<LslBoardComm>  boardFreeList = null;
		System.out.println("LslDaoImpl boardFreeList Start...");
		try {
			boardFreeList  = session.selectList("slBoardFreeList", boardComm);
			System.out.println("LslDaoImpl boardFreeList boardFreeList.size()" + boardFreeList.size());
			
		} catch (Exception e) {
			System.out.println("LslDaoImpl boardFreeList e.getMessage() -> " + e.getMessage());
			
		}
		return boardFreeList;
	}

	@Override
	public void saveBoardFile(LslBoardCommFile boardCommFile) {
		System.out.println("LslDaoImpl saveBoardFile Start...");
		
		session.insert("slBoardFileSave", boardCommFile);
		
	}

	@Override
	public void saveBoard(LslBoardComm boardComm) {
		System.out.println("LslDaoImpl saveBoard Start...");
		
		session.insert("slBoardSave", boardComm);
		
	}

}
