package com.blackberry.s20240130103.lsl.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LslDaoImpl implements LslDao {

	private final SqlSession session;

	

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

	@Override
	public LslBoardComm boardAskContents(int cboard_no) {
		System.out.println("LslDaoImpl boardAskContents Start...");
		LslBoardComm boardAskContents = session.selectOne("slboardAskContents", cboard_no);
		System.out.println("LslDaoImpl boardAskContents -> " + boardAskContents);
		return boardAskContents;
	}

	@Override
	public LslBoardComm boardFreeContents(int cboard_no) {
		System.out.println("LslDaoImpl boardAskContents Start...");
		    
		LslBoardComm boardFreeContents = session.selectOne("slboardFreeContents", cboard_no);
		System.out.println("LslDaoImpl boardAskContents -> " + boardFreeContents);
		return boardFreeContents;
	}

	@Override
	public int askBoardWrite(String boardtype) {
		System.out.println("LslDaoImpl askBoardWrite Start...");
		
		int askBoardWrite = session.insert("slaskBoardWrite", boardtype);

		
		return askBoardWrite;
	}

	

	@Override
	public List<LslCommReply> replyBoardAskList(int cboard_no) {
		List<LslCommReply> replyBoardAskList = session.selectList("slreplyBoardAskList", cboard_no);		
		
		return replyBoardAskList;
	}

	@Override
	public int freeBoardWrite(String boardtype) {
		System.out.println("LslDaoImpl freeBoardWrite Start...");
		
		int freeBoardWrite = session.insert("slfreeBoardWrite", boardtype);

		
		return freeBoardWrite;
	}

	@Override
	public int boardFreeViewCnt(LslBoardComm lslBoardComm) {
		
		int boardFreeViewCnt = session.update("slboardFreeViewCnt", lslBoardComm);
		
		return boardFreeViewCnt;
	}



	
// Rest API 
	
	// 상세내역 댓글 리스트 
	@Override
	public List<LslCommReply> replyBoardFreeList(int cboard_no) {
		List<LslCommReply> replyBoardFreeList = session.selectList("slreplyBoardFreeList", cboard_no);
		
		return replyBoardFreeList;
	}
	
	// insert 
	@Override
	public int insertBoardReply(LslCommReply lslCommReply) {
		int boardFreeAskResult = session.insert("slinsertBoardReply",lslCommReply);
		
		return boardFreeAskResult;
	}

	
	
	
	
	
}
