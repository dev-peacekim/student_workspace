package com.blackberry.s20240130103.ykm.service;

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
	public int writePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl writePost start---*");
		int result = ykmBoardDao.writePost(ykmBoardComm);
		return result;
	}

	@Override
	public List<YkmBoardComm> getPostList() {
		System.out.println("YkmServiceImpl getPostList start---*");
		List<YkmBoardComm> getPostList = ykmBoardDao.getPostList();
		
		System.out.println("YkmServiceImpl getPostList result --> " + getPostList.size());
		return getPostList;
	}

	@Override
	public YkmBoardComm getPost(int cboard_no) {
		return ykmBoardDao.getPost(cboard_no);
	}
	
	/*
	Iterator<YkmBoardComm> boardCommIt = getPostList.iterator();
	
	while(boardCommIt.hasNext()) {
		YkmBoardComm board = boardCommIt.next();
		board.setCountComment(ykmBoardDao.countComment(board.getCboard_no()));
	}
	*/
	
	@Override
	public int updatePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl updatePost start---*");
		System.out.println("YkmServiceImpl updatePost ykmBoardComm --> "+ ykmBoardComm);
		return ykmBoardDao.updatePost(ykmBoardComm);	
	}

	@Override
	public int deletePost(int cboard_no) {
		System.out.println("YkmServiceImpl deletePost start---*");
		return ykmBoardDao.deletePost(cboard_no);
	}
	
	@Override
	public int increseViewCount(int cboard_no) {
		return ykmBoardDao.increseViewcount(cboard_no);
	}

	
	
	// Reply RESTful API

	@Override
	public List<YkmBoardCommReply> getCommentList(int cboard_no) {
		List<YkmBoardCommReply> getCommentList = ykmBoardDao.getCommentList(cboard_no);
		System.err.println("YkmServiceImpl getCommentList result --> " + getCommentList.size());
		return getCommentList;
	}

	@Override
	public int writeComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.writeComment(ykmBoardCommReply);
	}

	@Override
	public int deleteComment(int creply_no) {
		return ykmBoardDao.deleteComment(creply_no);
	}

	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.updateComment(ykmBoardCommReply);
	}

	@Override
	public int countComment(int cboard_no) {
		return ykmBoardDao.countComment(cboard_no);
	}

	@Override
	public int getPostWriter(int cboard_no) {
		System.out.println("YkmServiceImpl getPostWriter start---*");
		return ykmBoardDao.getPostWriter(cboard_no);
	}
	
	// 모집여부 업데이트
	@Override
	public int updateRecruitment(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl updateRecruitment start---*");
		return ykmBoardDao.updateRecruitment(ykmBoardComm);
	}

	@Override
	public int getRecruitment(int cboard_no) {
		System.out.println("YkmServiceImpl getRecruitment start---*");
		return ykmBoardDao.getRecruitment(cboard_no);
	}


	
	
	
	
	
	
	
	
	
	
	
	
}