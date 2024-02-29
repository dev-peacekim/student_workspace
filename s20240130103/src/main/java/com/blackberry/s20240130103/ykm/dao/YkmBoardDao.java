package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;

public interface YkmBoardDao {
	int insertBoardStudyPost(YkmBoardComm ykmBoardComm);

	List<YkmBoardComm> spreadBoardList();

	List<YkmBoardComm> renderPostContent(int cboard_no);
	
}
