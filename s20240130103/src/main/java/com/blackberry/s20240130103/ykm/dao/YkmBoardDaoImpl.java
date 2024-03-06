package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class YkmBoardDaoImpl implements YkmBoardDao {

	private final SqlSession session;

	@Override
	public List<YkmBoardComm> getPostList() {
		System.out.println("YkmBoardDaoImpl getPostList start ---*");
		List<YkmBoardComm> getPostList = session.selectList("ykmGetPostList");
		System.out.println("YkmBoardDaoImpl getPostList result --> " + getPostList.size());

		return getPostList;
	}

	@Override
	public YkmBoardComm getPost(int cboard_no) {
		System.out.println("YkmBoardDaoImpl getPost start ---*");
		YkmBoardComm getPost = session.selectOne("ykmGetPost", cboard_no);
		// System.out.println("getPost result --> "+getPost);
		return getPost;
	}

	@Override
	public int writePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl writePost start---*");
		int result = session.insert("ykmWritePost", ykmBoardComm);
		System.out.println("YkmBoardDaoImpl writePost result=>" + result);
		return result;
	}

	@Override
	public int updatePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl updatePost start ---*");
		int updatePost = session.update("ykmUpdatePost", ykmBoardComm);
		return updatePost;
	}

	@Override
	public int deletePost(int cboard_no) {
		System.out.println("YkmBoardDaoImpl deletePost start ---*");
		int deletePost = session.delete("ykmDeletePost", cboard_no);
		System.out.println("YkmBoardDaoImpl deletePost deletePost --> " + deletePost);
		return deletePost;
	}

	@Override
	public int increseViewcount(int cboard_no) {
		return session.update("ykmIncreseViewCount", cboard_no);
	}


	// 댓글
	@Override
	public List<YkmBoardCommReply> getCommentList(int cboard_no) {
		System.out.println("YkmBoardDaoImpl getCommentList start ---*");
		List<YkmBoardCommReply> getCommentList = session.selectList("ykmGetCommentList", cboard_no);
		System.out.println("YkmBoardDaoImpl getCommentList finish ---*");
		return getCommentList;
	}

	@Override
	public int writeComment(YkmBoardCommReply ykmBoardCommReply) {
		System.out.println("YkmBoardDaoImpl writeComment start ---*");
		int result = session.insert("ykmWriteComment", ykmBoardCommReply);
		System.out.println("YkmBoardDaoImpl writeComment --> " + result);
		System.out.println("YkmBoardDaoImpl writeComment finish ---*");
		return result;
	}

	@Override
	public int deleteComment(int creply_no) {
		System.out.println("ykmBoardDaoImpl deleteComment start ---*");
		int result = session.delete("YkmDeleteComment", creply_no);
		return result;
	}

	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return session.update("ykmUpdateComment", ykmBoardCommReply);
	}

}
