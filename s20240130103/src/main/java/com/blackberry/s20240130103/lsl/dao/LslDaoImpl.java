package com.blackberry.s20240130103.lsl.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;
import com.blackberry.s20240130103.lsl.model.LslboardFile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LslDaoImpl implements LslDao {

	private final SqlSession session;

	
	// 자유 게시판 토탈 카운트 및 페이징 
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

	
	// 질문 게시판 토탈 카운트 및 페이징 
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
	
	
	// 게시판 검색
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

	
	// 게시판 글 상세 페이지 
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
	
	// 게시판 파일 업로드 
	@Override
	public void saveBoardFile(LslboardFile lslboardFile) {
			try {
				session.insert("slSaveBoarAskdFile", lslboardFile);
				session.insert("slsaveBoardFreeFile", lslboardFile);
				System.out.println("LslDaoImpl saveBoardFile Start ->" + lslboardFile);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("파일 DB 저장 오류 ->" + e.getMessage());
			}
		
	}


	// 게시판 글 상세 페이지 댓글 카운트
	@Override
	public int boardReplyCnt(int cboard_no) {
		int boardReplyCnt = session.selectOne("slboardReplyCnt", cboard_no);
		return boardReplyCnt;
	}

	
	
	
	// 게시판 글쓰기 
	@Override
	public int boardFreeWriteInsert(LslBoardComm lslBoardComms) {
		int boardFreeWriteInsert = session.insert("slboardFreeWriteInsert",lslBoardComms);
		return boardFreeWriteInsert;
	}
	
	@Override
	public int boardAskWriteInsert(LslBoardComm lslBoardComm) {
		int boardAskWriteInsert = session.insert("slboardAskWriteInsert", lslBoardComm);
		return boardAskWriteInsert;
	}


	// 게시판 글 삭제
	@Override
	public int deleteFreeBoard(LslBoardComm lslBoardComms) {
		int deleteFreeBoard = session.update("sldeleteFreeBoard", lslBoardComms);
		return deleteFreeBoard;
	}
	
	@Override
	public int deleteAskBoard(LslBoardComm lslBoardComm) {
		int deleteAskBoard = session.update("sldeleteAskBoard", lslBoardComm);
		return deleteAskBoard;
	}



	// 게시판 조회수 
	@Override
	public int boardFreeViewCnt(LslBoardComm lslBoardComm) {
		int boardFreeViewCnt = session.update("slboardFreeViewCnt", lslBoardComm);
		return boardFreeViewCnt;
	}

	@Override
	public int boardAskViewCnt(LslBoardComm lslBoardComm) {
		int boardAskViewCnt = session.update("slboardAskViewCnt", lslBoardComm);
		return boardAskViewCnt;
	}

	
	// 게시판 글 수정 페이지
	@Override
	public LslBoardComm boardFreeModify(int cboard_no) {
		LslBoardComm boardFreeModify = session.selectOne("slboardFreeModify",cboard_no);
		return boardFreeModify;
	}
	
	@Override
	public LslBoardComm boardAskModify(int cboard_no) {
		LslBoardComm boardAskModify = session.selectOne("slboardAskModify", cboard_no);
		return boardAskModify;
	}

	// 게시판 글 수정 
	@Override
	public int  boardFreeUpdate(LslBoardComm lslBoardComm) {
		int boardFreeUpdate = session.update("slboardFreeUpdate", lslBoardComm);
		return boardFreeUpdate;
	}
	
	@Override
	public int boardAskUpdate(LslBoardComm lslBoardComm) {
		int boardAskUpdate = session.update("slboardAskUpdate", lslBoardComm);
		return boardAskUpdate;
	}
	
	
	
	
	
// Rest API  댓글 
	
	// 게시판 댓글 리스트 
	@Override
	public List<LslCommReply> replyBoardFreeAskList(int cboard_no) {
		List<LslCommReply> replyBoardFreeAskList = session.selectList("slreplyBoardFreeAskList", cboard_no);
		
		return replyBoardFreeAskList;
	}
	
	
	// 게시판 댓글 등록  
	@Override
	public int insertBoardReply(LslCommReply lslCommReply) {
		int boardFreeAskResult = session.insert("slInsertBoardReply",lslCommReply);
		
		return boardFreeAskResult;
	}

	// 게시판 댓글 삭제 
	@Override
	public int deleteBoardReply(int creply_no) {
		int boardFreeAskResult = session.update("slDeleteBoardReply", creply_no);
		return boardFreeAskResult;
	}

	// 게시판 댓글 수정 
	@Override
	public int modifyBoardReply(LslCommReply lslCommReply) {
		System.out.println("modifyBoardReply lslCommReply : " + lslCommReply);
		int boardFreeAskResult = session.update("slmodifyBoardReply", lslCommReply);
		return boardFreeAskResult;
	}

	

	
	
	

	



	



	
	


	
	
	
	
	
}
