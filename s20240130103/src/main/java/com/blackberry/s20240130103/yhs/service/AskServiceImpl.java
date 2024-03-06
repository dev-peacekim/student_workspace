package com.blackberry.s20240130103.yhs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.yhs.dao.AskDao;
import com.blackberry.s20240130103.yhs.model.Ask;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AskServiceImpl implements AskService {
	
	private final AskDao askDao;
	
	@Override
	public int totalAsk() {
		System.out.println("AskServiceImpl Start total...");
		int totAskCnt = askDao.totalAsk();
		System.out.println("AskSeviceImpl totalAsk totAskCnt->"+ totAskCnt);
		return totAskCnt;
	}
	
	@Override
	public List<Ask> listAsk(Ask ask) {
		List<Ask> askList = null;
		System.out.println("AskServiceImp1 listManager start...");
		askList = askDao.listAsk(ask);
		System.out.println("AskServiceImp1 listAsk askList.size()->"+askList.size());
		return askList;
	}
	
	@Override
	public Ask askContent(String admin_title) {
		System.out.println("AskServiceImpl askContent Start...");
		Ask askContent = askDao.askContent(admin_title);
		
		System.out.println("AskServiceImpl askContent -> " + askContent);
		
		return askContent;
	}

	public List<Ask> askListSearch(Ask ask) {	
		 List<Ask> askListSearch = null;
		 System.out.println("LslServiceImpl boardFreeSearch Start...");
		  askListSearch = askDao.askListSearch(ask);
		  System.out.println("EmpServiceImpl boardFreeSearch.size() ->"+askListSearch.size());
		 return askListSearch;
	}

	public int insertAsk(Ask ask) {
		int result = 0;
		System.out.println("AskServiceImpl insertAsk start...");
		result = askDao.insertAsk(ask);
		return result;
	}

	}

