package com.blackberry.s20240130103.yhs.dao;

import java.util.List;

import com.blackberry.s20240130103.yhs.model.Ask;


public interface AskDao {

	int totalAsk();
	
	List<Ask> listAsk(Ask ask);

	Ask askContent(String admin_title);

	int askForm(String boardtype);
	
	
}
