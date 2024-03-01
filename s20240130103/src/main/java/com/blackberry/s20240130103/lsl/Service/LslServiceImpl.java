package com.blackberry.s20240130103.lsl.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.lsl.dao.LslDao;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LslServiceImpl implements LslService {

	private final LslDao ld;

	@Override
	public int boardAskWrite(LslBoardComm lslBoardComm, Long user_no) {
		int insertResult = 0;
		System.out.println("LslServiceImpl freeBoardWrite Start...");
		insertResult = ld.boardAskWrite(lslBoardComm, user_no);
		return insertResult;
	}

	@Override
	public int totalBoardFree() {
		System.out.println("LslServiceImpl totalBoardFree Start...");
		int totalBoardFreeCnt = ld.totalBoardFree();
		System.out.println("LslServiceImpl totalBoardFreeListCnt -> " + totalBoardFreeCnt);
	
		return totalBoardFreeCnt;
	}

	@Override
	public List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm) {
		List<LslBoardComm> boardFreeList = null;
		boardFreeList = ld.boardFreeList(lslBoardComm);
		System.out.println("LslServiceImpl boardFreeList.size() ->" + boardFreeList.size());
		
		return boardFreeList;
	}
	
	@Override
	public List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm) {
	 List<LslBoardComm> boardFreeSearch = null;
	 System.out.println("LslServiceImpl boardFreeSearch Start...");
	  boardFreeSearch = ld.boardFreeSearch(lslBoardComm);
	  System.out.println("EmpServiceImpl boardFreeSearch.size() ->"+boardFreeSearch.size());
	 return boardFreeSearch;
	}


	

	@Override
	public int totalBoardAsk() {
		System.out.println("LslServiceImpl totalBoardAsk Start...");
		int totalBoardAskCnt = ld.totalBoardAsk();
		System.out.println("LslServiceImpl totalBoardAskCnt -> "+totalBoardAskCnt);
		
		
		return totalBoardAskCnt;
	}

	@Override
	public List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm) {
	 List<LslBoardComm> boardAskList = null;
	 boardAskList = ld.boardAskList(lslBoardComm);
	 System.out.println("LslServiceImpl boardAskList.size() ->" + boardAskList.size());
	 
	
		return boardAskList;
	}

	@Override
	public List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm) {
		 List<LslBoardComm> boardAskSearch = null;
		 System.out.println("LslServiceImpl boardAskSearch Start...");
		 boardAskSearch = ld.boardAskSearch(lslBoardComm);
		  System.out.println("EmpServiceImpl boardAskSearch.size() ->"+boardAskSearch.size());
		 return boardAskSearch;
	}

	@Override
	public LslBoardComm boardAskContents(int cboard_no) {
		System.out.println("LslServiceImpl boardAskContents Start...");
		LslBoardComm boardAskContents = ld.boardAskContents(cboard_no);
		
		System.out.println("EmpServiceImpl boardAskContents -> " + boardAskContents);
		
		return boardAskContents;
	}

	@Override
	public LslBoardComm boardFreeContents(int cboard_no) {
		System.out.println("LslServiceImpl boardAskContents Start...");
		LslBoardComm boardFreeContents = ld.boardFreeContents(cboard_no);
		
		System.out.println("EmpServiceImpl boardAskContents -> " + boardFreeContents);
		
		return boardFreeContents;
	}

	

}
