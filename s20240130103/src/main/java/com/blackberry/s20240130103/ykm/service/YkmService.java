package com.blackberry.s20240130103.ykm.service;

import java.util.List;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmService {

	// 게시판 
	int writePost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> getPostList();
	
	YkmBoardComm getPost(int cboard_no);
	
	int updatePost(YkmBoardComm ykmBoardComm);

	int deletePost(int cboard_no);

	
	
	// 댓글 
	List<YkmBoardCommReply> getCommentList(int cboard_no);

	int writeComment(YkmBoardCommReply ykmBoardCommReply);

	int deleteComment(int creply_no);

	int updateComment(YkmBoardCommReply ykmBoardCommReply);


	

	





	
	


}
