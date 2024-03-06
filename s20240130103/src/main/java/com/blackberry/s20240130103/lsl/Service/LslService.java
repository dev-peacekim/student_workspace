package com.blackberry.s20240130103.lsl.Service;

import java.util.List;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

public interface LslService {

	int totalBoardFree();

	List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm);

	int totalBoardAsk();

	List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm);
	


	List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm);

	List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm);




	LslBoardComm boardFreeContents(int cboard_no);

	LslBoardComm boardAskContents(int cboard_no);
	
	

	int askBoardWrite(String boardtype);
	int freeBoardWrite(String boardtype);
	
	
	


	int boardFreeViewCnt(LslBoardComm lslBoardComm);
	

	
	// 댓글 
	
	// 댓글 리스트 
	List<LslCommReply> replyBoardFreeAskList(int cboard_no);

	// 댓글 등록
	int insertBoardReply(LslCommReply lslCommReply);
	// 댓글 삭제 
	int deleteBoardReply(int creply_no);





}
