package com.blackberry.s20240130103.lsl.Service;


import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.lsl.dao.LslDao;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LslServiceImpl implements LslService {

	private final LslDao ld;

	@Override
	public int freeBoardWrite(LslBoardComm lslBoardComm) {
		int insertResult = 0;
		System.out.println("LslServiceImpl freeBoardWrite Start...");
		insertResult = ld.freeBoardWrite(lslBoardComm);
		return insertResult;
	}



}
