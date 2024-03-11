package com.blackberry.s20240130103.yhs.service;

import java.util.List;

import com.blackberry.s20240130103.yhs.model.Ask;


public interface AskService {

	int totalAsk(Ask ask);
	
	List<Ask> listAsk(Ask ask);
	
	Ask askContent(String admin_title);
	
	int insertAsk(Ask ask);
	
}
