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
	public int insertBoardStudyPost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardDaoImpl insertBoardStudyPost start---*");
		int result = session.insert("ykmBoardStudyPost", ykmBoardComm);
		System.out.println("YkmBoardDaoImpl insertBoardStudyPost result=>" + result);
		return result;
	}

	@Override
	public List<YkmBoardComm> renderBoardList() {
		System.out.println("YkmBoardDaoImpl renderBoardList start ---*");
		List<YkmBoardComm> renderBoardList = session.selectList("ykmRenderList");
		System.out.println("YkmBoardDaoImpl renderBoardList result --> " +renderBoardList.size());

		return renderBoardList;
	}

	@Override
	public YkmBoardComm renderPostContent(int cboard_no) {
		System.out.println("YkmBoardDaoImpl renderPostContent start ---*");
		YkmBoardComm renderPostContent = session.selectOne("ykmRenderPost", cboard_no);
		// System.out.println("renderPostContent result --> "+renderPostContent);
		return renderPostContent;
	}
	
	@Override
	public YkmBoardComm getPost(int cboard_no) {
		return session.selectOne("YkmGetPost", cboard_no);
	}

	// 댓글
	@Override
	public List<YkmBoardCommReply> renderReplyList(int cboard_no) {
		System.out.println("YkmBoardDaoImpl renderReplyList start ---*");
		List<YkmBoardCommReply> renderReplyList = session.selectList("ykmRednerReplyList", cboard_no);
		// System.out.println("YkmBoardDaoImpl renderReplyList finish ---*");
		return renderReplyList;
	}

	@Override
	public int insertComment(YkmBoardCommReply ykmBoardCommReply) {
		System.out.println("YkmBoardDaoImpl insertComment start ---*");
		int result = session.insert("YkminsertComment", ykmBoardCommReply);
		return result;
	}

	@Override
	public int deleteComment(int creply_no) {
		System.out.println("YkmBoardDaoImpl deleteComment start ---*");
		int result = session.delete("YkmdeleteComment", creply_no);
		return result;
	}

	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		int result = session.update("YkmupdateComment", ykmBoardCommReply);
		return result;
	}

	

}
