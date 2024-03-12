package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmBoardDao {
	int writePost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm);

	YkmBoardComm getPost(int cboard_no);

	int updatePost(YkmBoardComm ykmBoardComm);
	
	int deletePost(int cboard_no);
	
	int increseViewcount(int cboard_no);
	
	int updateRecruitment(YkmBoardComm ykmBoardComm); // 모집여부
	
	int saveFileList(YkmBoardCommFile ykmBoardCommFile); // 파일 DB 저장
	
	
	/* REST API	*/
	// 댓글
	List<YkmBoardCommReply> getCommentList(int cboard_no);

	int writeComment(YkmBoardCommReply ykmBoardCommReply);

	int deleteComment(int creply_no);

	int updateComment(YkmBoardCommReply ykmBoardCommReply);

	int countComment(int cboard_no);

	List<YkmBoardComm> getSearchList(YkmBoardComm ykmBoardComm); // 검색 결과 조회

	int getTotalCount(YkmBoardComm ykmBoardComm);

	List<YkmBoardCommFile> getFileList(int cboard_no);


}
