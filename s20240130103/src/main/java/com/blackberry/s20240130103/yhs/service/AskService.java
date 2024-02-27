package com.blackberry.s20240130103.yhs.service;

import java.util.List;
import java.util.Optional;

import com.blackberry.s20240130103.yhs.model.Ask;

public interface AskService {

	List<Ask> getListAllAsk();
	
	Optional<Ask> findById(Long askId);
	
	void askUpdate(Ask ask);

}
