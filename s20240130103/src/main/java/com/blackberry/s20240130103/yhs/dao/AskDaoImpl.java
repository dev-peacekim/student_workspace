package com.blackberry.s20240130103.yhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.yhs.model.Ask;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class AskDaoImpl implements AskDao {
	// Mybatis DB 연동 
    private final SqlSession session;
	@Override
	public int totalAsk() {
		int totAskCount = 0;
		System.out.println("AskDaoImpl Start total..." );

		try {
			totAskCount = session.selectOne("com.blackberry.s20240130103.yhs.AskMapper");
			System.out.println("AskDaoImpl totalAsk totAskCount->" +totAskCount);
		} catch (Exception e) {
			System.out.println("AskDaoImpl totalAsk Exception->"+e.getMessage());
		}
		return totAskCount;	
	}
	
	@Override
	public List<Ask> listAsk(Ask ask) {
		List<Ask> askList = null;
		System.out.println("AskDaoImpl listAsk Start ..." );
		try {
			//                             Map ID        parameter
			askList = session.selectList("yhsAskListAll", ask);
			System.out.println("AskDaoImpl listAsk askList.size()->"+askList.size());
		} catch (Exception e) {
			System.out.println("AskDaoImpl listAsk e.getMessage()->"+e.getMessage());
		}
		return askList;
	}
	
	
	 @Override 
	 public Ask askContent(String admin_title) {
	 System.out.println("AskDaoImpl askContent Start..."); 
	 System.out.println("AskDaoImpl askConten admin_title->"+admin_title);

	 Ask askContent = session.selectOne("yhsAskContent", admin_title);
	 System.out.println("AskDaoImpl askContent -> " + askContent); 
	 return askContent; 
	 }

	@Override
	public int askForm(String boardtype) {
		System.out.println("AskDaoImpl AskForm Start...");
		
		int askForm = session.insert("askForm", boardtype);
	
		return askForm;
	}
	 
		

}
