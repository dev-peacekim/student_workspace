package com.blackberry.s20240130103.ykm.service;

import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.ykm.dao.YkmBoardDao;
import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YkmServiceImpl implements YkmService {

	private final YkmBoardDao ykmBoardDao;

	@Override
	public int insertBoardStudyPost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl insertBoardStudyPost start---*");
		int result = ykmBoardDao.insertBoardStudyPost(ykmBoardComm);
		return result;
	}

	@Override
	public List<YkmBoardComm> spreadBoardList() {
		List<YkmBoardComm> spreadBoardList = ykmBoardDao.spreadBoardList();
		Iterator<YkmBoardComm> boardIter = spreadBoardList.iterator();
		while (boardIter.hasNext()) {
			YkmBoardComm ykmBoardComm = boardIter.next();
			//ykmBoardComm.setCboard_date(ykmBoardComm.getCboard_date().substring(0, 10));
		}
		return spreadBoardList;
	}

	@Override
	public YkmBoardComm renderPostContent(int cboard_no) {
		YkmBoardComm renderPostContent = ykmBoardDao.renderPostContent(cboard_no);
		return renderPostContent;
	}

	@Override
	public void insertStudyPostComment(YkmBoardCommReply ykmBoardCommReply) {
		
		
	}

	
	
}
