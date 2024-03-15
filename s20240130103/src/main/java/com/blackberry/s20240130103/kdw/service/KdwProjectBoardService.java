package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.BoardProject;

public interface KdwProjectBoardService {
	
	// 게시글 개수
	int totPboardListCnt(Long userNo, Long projectNo);
	// 게시글 리스트
	List<BoardProject> getProjectBoardList(Long userNo, Long projectNo, int start, int end);
	
	// ! ========== 검색기능 ========== !
	// 검색한 게시글 개수
	int searchPboardListCnt(Long userNo, Long projectNo, String keyword, String type);
	// 검색한 게시글 리스트
	List<BoardProject> searchProjectBoardList(Long userNo, Long projectNo, String keyword, String type, int start, int end);
	
	// 글쓰기 + 업로드
	void writeSave(BoardProject boardProject, MultipartFile[] files, String path);

}
