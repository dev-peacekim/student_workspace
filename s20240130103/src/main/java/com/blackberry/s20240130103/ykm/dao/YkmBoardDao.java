package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmBoardDao {
	int writePost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> getPostList();

	YkmBoardComm getPost(int cboard_no);

	int updatePost(YkmBoardComm ykmBoardComm);
	
	int deletePost(int cboard_no);
	
	int increseViewcount(int cboard_no);
	
	
	/* REST API	*/
	// 댓글
	List<YkmBoardCommReply> getCommentList(int cboard_no);

	int writeComment(YkmBoardCommReply ykmBoardCommReply);

	int deleteComment(int creply_no);

	int updateComment(YkmBoardCommReply ykmBoardCommReply);

	int countComment(int cboard_no);

	int getPostWriter(int cboard_no); // 글 작성자 정보


	
	// 글
	int updateRecruitment(YkmBoardComm ykmBoardComm); // 모집여부

	int getRecruitment(int cboard_no);  // 모집 여부 확인 
	
	


}
