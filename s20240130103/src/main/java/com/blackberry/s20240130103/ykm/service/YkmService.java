package com.blackberry.s20240130103.ykm.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;
import com.blackberry.s20240130103.ykm.model.YkmPaging;

public interface YkmService {

	// 게시판 
	int writePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList);

	List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm);
	
	YkmBoardComm getPost(int cboard_no);
	
	int updatePost(YkmBoardComm ykmBoardComm);

	int deletePost(int cboard_no);
	
	int increseViewCount(int cboard_no); // 조회수

	//Map<String, Object> getFileList(int cboard_no); // 파일 보여주기

	
	
	// 댓글 
	List<YkmBoardCommReply> getCommentList(int cboard_no);

	int writeComment(YkmBoardCommReply ykmBoardCommReply);

	int deleteComment(int creply_no);

	int updateComment(YkmBoardCommReply ykmBoardCommReply);

	int countComment(int cboard_no); // 댓글 개수 카운트

	int updateRecruitment(YkmBoardComm ykmBoardComm); // 모집여부 값 변경

	List<YkmBoardComm> getSearchList(YkmBoardComm ykmBoardComm); // 검색 결과 조회

	int getTotalCount(YkmBoardComm ykmBoardComm);


	
}
