package com.blackberry.s20240130103.lsl.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LslDaoImpl implements LslDao {

	private final SqlSession session;

	@Override
	public int boardAskWrite(LslBoardComm lslBoardComm, Long user_no) {
	    System.out.println("LslDaoImpl freeBoardWrite Start...");
	    
	    // 게시글 삽입
	    int insertResult = session.insert("lslAskBoardWrite", lslBoardComm);
	    
	    // 파일 정보 삽입
	    if (insertResult > 0) {
	        // 게시글이 성공적으로 삽입되었을 때만 파일 정보를 삽입
	        int fileInsertResult = session.insert("lslAskBoardFile", lslBoardComm);
	        if (fileInsertResult <= 0) {
	            // 파일 정보 삽입 실패 처리
	           
	        }
	    } else {
	       
	    }
	    
	    return insertResult;
	}

	@Override
	public int totalBoardFree() {
		int totalBoardFreeCnt  = 0;
		System.out.println("LslDaoImpl totalBoardFreeList Start...");
		try {
			totalBoardFreeCnt = session.selectOne("sltotalBoardFreeList");
			System.out.println("LslDaoImpl totalBoardFreeListCnt -> "+ totalBoardFreeCnt);
		} catch (Exception e) {
			System.out.println("LslDaoImpl totalBoardFreeList Exception ->" + e.getMessage());
		}
		return totalBoardFreeCnt;
	}

	@Override
	public List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm) {
		List<LslBoardComm> boardFreeList = null;
		System.out.println("LslDaoImpl boardFreeList Start...");
		
		try {
			boardFreeList = session.selectList("slBoardFreeListAll", lslBoardComm);
			System.out.println("LslDaoImpl boardFreeList.size() ->" + boardFreeList.size());
			System.out.println("DaoList impl board : " + boardFreeList.get(0));
		} catch (Exception e) {
			System.out.println("LslDaoImpl boardFreeList Exception ->" + e.getMessage());
		}
		return boardFreeList;
	}

	@Override
	public int totalBoardAsk() {
		int totalBoardAskCnt = 0;
		System.out.println("LslDaoImpl totalBoardAsk Start...");
		try {
			totalBoardAskCnt= session.selectOne("sltotalBoardAskList");
			System.out.println("LslDaoImpl totalBoardAskCnt -> " + totalBoardAskCnt);
		} catch (Exception e) {
			System.out.println("LslDaoImpl totalBoardAsk Exception -> " + e.getMessage());
		}
		return totalBoardAskCnt;
	}

	@Override
	public List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm) {
		List<LslBoardComm> boardAskList = null;
		System.out.println("LslDaoImpl boardAskList Start..");
		try {
			boardAskList = session.selectList("slBoardAskListAll", lslBoardComm);
			System.out.println("LslDaoImpl boardAskList.size() ->" + boardAskList.size());
		} catch (Exception e) {
			System.out.println("LslDaoImpl boardAskList Exception -> " + e.getMessage());
		}
		return boardAskList;
	}

	@Override
	public List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm) {
		List<LslBoardComm> boardFreeSearch = null;
		System.out.println("LslDaoImpl boardFreeSearch Start...");
		
		try {
			boardFreeSearch = session.selectList("slboardFreeSearch", lslBoardComm);
		} catch (Exception e) {
			System.out.println("LslDaoImpl boardFreeSearch Exception ->" + e.getMessage());
		}
		return boardFreeSearch;
	}

	@Override
	public List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm) {
		List<LslBoardComm> boardAskSearch = null;
		System.out.println("LslDaoImpl boardAskSearch Start...");
		
		try {
			boardAskSearch = session.selectList("slboardAskSearch", lslBoardComm);
		} catch (Exception e) {
			System.out.println("LslDaoImpl boardAskSearch Exception ->" + e.getMessage());
		}
		return boardAskSearch;
	}


	

}
