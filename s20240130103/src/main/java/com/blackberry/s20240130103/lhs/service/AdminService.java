package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	Map<String, Long> selectTablesCnt();

	List<Map<String, Long>> selectUserJoinCnt();

}
