package com.blackberry.s20240130103.kdw.dao;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.BoardProject;

public interface KdwBoardProjectDao {
	
	// 게시글 개수
	int totPboardListCnt(Long userNo);
	// 게시글 리스트
	List<BoardProject> getProjectBoardList(Long userNo, int start, int end);

}
