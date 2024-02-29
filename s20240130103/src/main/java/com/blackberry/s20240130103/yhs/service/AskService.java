package com.blackberry.s20240130103.yhs.service;

import java.util.List;

import com.blackberry.s20240130103.yhs.model.Ask;


public interface AskService {

	int totalAsk();
	
	List<Ask> listAsk(Ask ask);
	
	Ask detailAsk(int user_no);
	
	
	

}
