package com.blackberry.s20240130103.lsl.Service;

import java.util.List;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;

public interface LslService {

	int     boardAskWrite(LslBoardComm lslBoardComm, Long user_no);

	int totalBoardFree();

	List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm);

	int totalBoardAsk();

	List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm);

	List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm);

	List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm);




	LslBoardComm boardFreeContents(int cboard_no);

	LslBoardComm boardAskContents(int cboard_no);


}
