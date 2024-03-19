package com.blackberry.s20240130103.ykm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmBoardDao {
	
	/* 스터디 게시판 */
	int writePost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm);

	YkmBoardComm getPost(int cboard_no);

	int updatePost(YkmBoardComm ykmBoardComm);
	
	int deletePost(int cboard_no);
	
	int increseViewcount(int cboard_no);
	
	int updateRecruitment(YkmBoardComm ykmBoardComm); // 모집여부
	
	int saveFileList(YkmBoardCommFile ykmBoardCommFile); // 파일 DB 저장
	
	
	/* 스터디 게시판 댓글 REST API	*/
	List<YkmBoardCommReply> getCommentList(int cboard_no);

	int writeComment(YkmBoardCommReply ykmBoardCommReply);

	int deleteComment(int creply_no);

	int updateComment(YkmBoardCommReply ykmBoardCommReply);

	int countComment(int cboard_no);

	int getTotalCount(YkmBoardComm ykmBoardComm); // (페이징) 전체 게시글 카운트

	List<YkmBoardCommFile> getFileList(int cboard_no); // 파일 리스트 불러오기

	int writeReply(YkmBoardCommReply ykmBoardCommReply); // 대댓글 > 중복이므로 수정 필요

	int getSearchCount(YkmBoardComm ykmBoardComm); // 검색 결과 게시글 조회

	
	
	/* 공모전 게시판 */
	List<YkmBoardComm> getCntPostList(YkmBoardComm ykmBoardComm); // 게시판 리스트 조회

	int getCntTotalCount(YkmBoardComm ykmBoardComm); // 전체 게시글 카운트

	int writeCntPost(YkmBoardComm ykmBoardComm); // 글 작성, 파일 업로드

	YkmBoardCommReply getReplyNo(YkmBoardCommReply ykmBoardCommReply); // 댓글 번호 조회

	int updateGroup(YkmBoardCommReply ykmBoardCommReply); // 같은 댓글 그룹핑

	
	

}
