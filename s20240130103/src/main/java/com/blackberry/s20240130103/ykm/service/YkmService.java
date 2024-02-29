package com.blackberry.s20240130103.ykm.service;

import java.util.List;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

public interface YkmService {

	int insertBoardStudyPost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> spreadBoardList();
	
	YkmBoardComm renderPostContent(int cboard_no);

	void insertStudyPostComment(YkmBoardCommReply ykmBoardCommReply);

}
