package com.blackberry.s20240130103.lsl.Service;

import java.util.List;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslBoardCommFile;

public interface LslService {

	List<LslBoardComm> getBoardFreeList(LslBoardComm boardComm);

	void saveBoardFile(LslBoardCommFile boardCommFile);

	void saveBoard(LslBoardComm boardComm);




}
