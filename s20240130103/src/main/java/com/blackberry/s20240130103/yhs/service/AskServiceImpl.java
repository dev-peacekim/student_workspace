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
	public List<Ask> getListAllAsk() {
		List<Ask> listAsk  = askDao.findAll();
		System.out.println("AskService getListAllAsk listAsk.size()->"+listAsk.size());
		return listAsk;
	}
	
	@Override
	public Optional<Ask> findById(Long askId) {
		System.out.println("AskService findById Start...");
		Optional<Ask> ask = askDao.findById(askId); 
		return ask;
	}
	
	@Override
	public void askUpdate(Ask ask) {
		System.out.println("AskService askUpdate ask.getName()->"+ask.getUser_no());
		System.out.println("AskService askUpdate ask.getId()->"+ask.getCol());
		askDao.updateByAsk(ask); 
		return;
	}
	
}
