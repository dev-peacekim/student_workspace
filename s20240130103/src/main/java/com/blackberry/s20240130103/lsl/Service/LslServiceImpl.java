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
	public List<LslBoardComm> getBoardFreeList(LslBoardComm boardComm) {
		List<LslBoardComm> boardFreeList =null;
		System.out.println("LslServiceImpl getBoardFreeList Start...");
		boardFreeList = ld.boardFreeList(boardComm);
		return boardFreeList;
	}

}
