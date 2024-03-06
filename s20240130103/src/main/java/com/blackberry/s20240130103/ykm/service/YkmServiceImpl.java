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
		
		
		/*
		Iterator<YkmBoardComm> boardIter = getPostList.iterator();
		while (boardIter.hasNext()) {
			YkmBoardComm ykmBoardComm = boardIter.next();
		}
		*/
		
		System.out.println("YkmServiceImpl getPostList result --> " + getPostList.size());
		return getPostList;
	}

	@Override
	public YkmBoardComm getPost(int cboard_no) {
		YkmBoardComm getPost = ykmBoardDao.getPost(cboard_no);
		return getPost;
	}
	


	@Override
	public int updatePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl updatePost start---*");
		int updatePost = ykmBoardDao.updatePost(ykmBoardComm);
		System.out.println("YkmServiceImpl updatePost ykmBoardComm --> "+ ykmBoardComm);
		System.out.println("YkmServiceImpl updatePost -->" +  updatePost);
		return updatePost;
	}

	@Override
	public int deletePost(int cboard_no) {
		System.out.println("YkmServiceImpl deletePost start---*");
		int deletePost = ykmBoardDao.deletePost(cboard_no);
		System.out.println("YkmServiceImpl deletePost --> "+deletePost);
		return deletePost;
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
		int result = ykmBoardDao.writeComment(ykmBoardCommReply);
		return result;
	}

	@Override
	public int deleteComment(int creply_no) {
		int result = ykmBoardDao.deleteComment(creply_no);
		return result;
	}

	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.updateComment(ykmBoardCommReply);
	}


	
	/*
 	1. 트랜잭션을 시작한다.
 	2. 댓글들을 삭제한다. (플래그 변경:0>1)
 	3. 게시글을 삭제한타. (플래그 변경:0>1)
 	4. 커밋한다.
 	5. 2~3 과정 중 오류가 발생하면 롤백한다.

	@Transactional
	public void deleteCommentAndChangeStatus(int creply_no) 
	// 게시글 삭제될 때 게시글에 해당되는 댓글도 다 삭제가 되어야함

	
	*/
}
