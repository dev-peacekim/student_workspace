package com.blackberry.s20240130103.yhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.yhs.domain.Ask;
import com.blackberry.s20240130103.yhs.repository.AskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AskService {
	
	private final AskRepository AskRepository;
	
	public List<Ask> getListAllAsk() {
		List<Ask> listAsk  = AskRepository.findAll();
		System.out.println("AskService getListAllAsk listAsk.size()->"+listAsk.size());
		return listAsk;
	}
	public Optional<Ask> findById(Long askId) {
		System.out.println("AskService findById Start...");
		Optional<Ask> ask = AskRepository.findById(askId); 
		return ask;
	}
	public void askUpdate(Ask ask) {
		System.out.println("AskService askUpdate ask.getName()->"+ask.getUser_no());
		System.out.println("AskService askUpdate ask.getId()->"+ask.getCol());
		AskRepository.updateByAsk(ask); 
		return;
	}
	
}
