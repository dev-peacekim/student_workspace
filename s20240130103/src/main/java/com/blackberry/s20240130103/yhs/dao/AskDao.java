package com.blackberry.s20240130103.yhs.dao;

import java.util.List;
import java.util.Optional;

import com.blackberry.s20240130103.yhs.model.Ask;


public interface AskDao {

	Ask save(Ask ask);
	List<Ask> findAll();
	Optional<Ask> findById(Long askId);
	void updateByAsk(Ask ask);
	
}
