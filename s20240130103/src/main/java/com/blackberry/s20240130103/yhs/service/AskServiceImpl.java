package com.blackberry.s20240130103.yhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		System.out.println("AskServiceImp1 listEmp askList.size()->"+askList.size());
		return askList;
	}
	

	public Ask detailAsk(long user_no) {
		Ask ask = null;
		ask = askDao.detailAsk(user_no);
		return ask;
	}

	@Override
	public Ask detailAsk(int user_no) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
