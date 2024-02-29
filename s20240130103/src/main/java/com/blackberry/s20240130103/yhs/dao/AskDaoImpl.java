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
		System.out.println("AskDaoImpl listEmp Start ..." );
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
	public Ask detailAsk(long user_no) {
		System.out.println("EmpDaoImpl detail start..");
		//Emp emp = null;
		Ask ask = new Ask();
		try {
			//                       mapper ID   ,    Parameter
			ask = session.selectOne("tkEmpSelOne",    user_no);
			System.out.println("AskDaoImpl detail getEname->"+ask.getAdmin_no());
		} catch (Exception e) {
			System.out.println("AskDaoImpl detail Exception->"+e.getMessage());
		}
		return ask;
	}
	


		

}
