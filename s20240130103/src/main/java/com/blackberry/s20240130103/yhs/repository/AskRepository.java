package com.blackberry.s20240130103.yhs.repository;

import java.util.List;
import java.util.Optional;

import com.blackberry.s20240130103.yhs.domain.Ask;

public interface AskRepository {
	Ask save(Ask ask);
	List<Ask> findAll();
	Optional<Ask> findById(Long askId);
	void updateByAsk(Ask ask);
}
