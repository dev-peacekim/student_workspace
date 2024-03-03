package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmBoardDao {
	int insertBoardStudyPost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> renderBoardList();

	YkmBoardComm renderPostContent(int cboard_no);
	
	YkmBoardComm getPost(int cboard_no);

	// 댓글
	List<YkmBoardCommReply> renderReplyList(int cboard_no);

	int insertComment(YkmBoardCommReply ykmBoardCommReply);

	int deleteComment(int creply_no);

	int updateComment(YkmBoardCommReply ykmBoardCommReply);

}
