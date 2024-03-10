package com.blackberry.s20240130103.lhs.service;

import java.util.List;
import java.util.Map;

import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.User;

public interface AdminService {

	Map<String, Long> selectTablesCnt();

	List<Map<String, Long>> selectUserJoinCnt();

	List<User> selectUsersDeleteRequest();

	List<BoardAdmin> selectBoardAdminList();

	int selectBoardCommCnt(BoardComm board);

	List<BoardComm> selectBoardCommList(BoardComm board);

	BoardComm selectBoard(BoardComm board);

	int deleteBoard(BoardComm board);

	int selectUsersCnt(User user);

	List<User> selectUsersList(User user);

}
