package com.blackberry.s20240130103.lhs.dao;

import java.util.List;
import java.util.Map;

public interface AdminDao {

	Map<String, Long> selectTablesCnt();

	List<Map<String, Long>> selectUserJoinCnt();

}
