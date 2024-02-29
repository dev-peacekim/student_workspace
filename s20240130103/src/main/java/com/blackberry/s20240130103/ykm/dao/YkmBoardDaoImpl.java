package com.blackberry.s20240130103.ykm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;

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
	public List<YkmBoardComm> spreadBoardList() {
		System.out.println("YkmBoardDaoImpl spreadBoardList ---*");
		List<YkmBoardComm> spreadBoardList = session.selectList("ykmSpreadList");
		System.out.println("spreadBoardList result --> " +spreadBoardList.size());

		return spreadBoardList;
	}

	@Override
	public List<YkmBoardComm> renderPostContent(int cboard_no) {
		System.out.println("YkmBoardDaoImpl renderPostContent ---*");
		List<YkmBoardComm> renderPostContent = session.selectList("ykmRenderPost", cboard_no);
		System.out.println("renderPostContent result --> "+renderPostContent.size());
		return renderPostContent;
	}


}
