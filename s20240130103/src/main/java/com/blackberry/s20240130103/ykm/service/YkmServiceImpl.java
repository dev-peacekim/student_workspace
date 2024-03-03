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

	// 스터디 게시판
	@Override
	public int insertBoardStudyPost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl insertBoardStudyPost start---*");
		int result = ykmBoardDao.insertBoardStudyPost(ykmBoardComm);
		return result;
	}

	@Override
	public List<YkmBoardComm> renderBoardList() {
		List<YkmBoardComm> renderBoardList = ykmBoardDao.renderBoardList();
		Iterator<YkmBoardComm> boardIter = renderBoardList.iterator();
		while (boardIter.hasNext()) {
			YkmBoardComm ykmBoardComm = boardIter.next();
			// ykmBoardComm.setCboard_date(ykmBoardComm.getCboard_date().substring(0, 10));
		}
		return renderBoardList;
	}

	@Override
	public YkmBoardComm renderPostContent(int cboard_no) {
		YkmBoardComm renderPostContent = ykmBoardDao.renderPostContent(cboard_no);
		return renderPostContent;
	}

	@Override
	public YkmBoardComm getPost(int cboard_no) {
		return ykmBoardDao.getPost(cboard_no);
	}

	// Reply RESTful API

	@Override
	public List<YkmBoardCommReply> renderReplyList(int cboard_no) {
		List<YkmBoardCommReply> renderReplyList = ykmBoardDao.renderReplyList(cboard_no);
		System.err.println("YkmServiceImpl renderReplyList --> " + renderReplyList.size());
		return renderReplyList;
	}

	@Override
	public int insertComment(YkmBoardCommReply ykmBoardCommReply) {
		int result = ykmBoardDao.insertComment(ykmBoardCommReply);
		return result;
	}

	@Override
	public int deleteComment(int creply_no) {
		int result = ykmBoardDao.deleteComment(creply_no);
		return result;
	}

	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		int result = ykmBoardDao.updateComment(ykmBoardCommReply);
		return result;
	}

}
