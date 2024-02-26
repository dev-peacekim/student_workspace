package com.blackberry.s20240130103.lsl.dao;

import java.util.List;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslBoardCommFile;

public interface LslDao {

	List<LslBoardComm> boardFreeList(LslBoardComm boardComm);

	void saveBoardFile(LslBoardCommFile boardCommFile);

	void saveBoard(LslBoardComm boardComm);


}
