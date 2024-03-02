package com.blackberry.s20240130103.lsl.dao;

import java.util.List;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

public interface LslDao {



	int totalBoardFree();


	List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm);


	int totalBoardAsk();


	List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm);


	List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm);


	List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm);


	LslBoardComm boardAskContents(int cboard_no);


	LslBoardComm boardFreeContents(int cboard_no);


	int askBoardWrite(String boardtype);


	List<LslCommReply> replyBoardFreeList(int cboard_no);


	List<LslCommReply> replyBoardAskList(int cboard_no);






}
