package com.blackberry.s20240130103.lhs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
	
	private final SqlSession session;

	@Override
	public Map<String, Long> selectTablesCnt() {
		Map<String, Long> tableCntMap = session.selectOne("LhsAdminCntMapget");
		System.out.println("AdminDaoImpl map size : " + tableCntMap.size());
		int i = 1;
		System.out.println("===AdminDaoImpl selectTablesCnt map values===");
		for(String key : tableCntMap.keySet()) {
			System.out.println("key value" + (i++) +" "+key +" : " + tableCntMap.get(key));
		}
		System.out.println("===AdminDaoImpl selectTablesCnt map end===");
		return tableCntMap;
	}
	
	@Override
	public List<Map<String, Long>> selectUserJoinCnt() {
		List<Map<String, Long>> userJoinCntList = session.selectList("LhsAdminJoinUserCnt5day");
		System.out.println("AdminDaoImpl List size : " + userJoinCntList.size());
		System.out.println("===AdminDaoImpl selectUserJoinCnt List Map values===");
		for(Map<String, Long> map : userJoinCntList) {
			for(String key : map.keySet()) {
				System.out.println("List Map key : " + key + " value : " + map.get(key));
			}
		}
		System.out.println("===AdminDaoImpl selectUserJoinCnt List map end===");
		return userJoinCntList;
	}
	
	@Override
	public List<User> selectUsersDeleteRequest() {
		List<User> deleteUserList = session.selectList("LhsAdminDeleteUserList");
		System.out.println("deleteUserList size : " + deleteUserList.size());
		return deleteUserList;
	}
	
	@Override
	public List<BoardAdmin> selectBoardAdminList() {
		List<BoardAdmin> boardAdminList = session.selectList("LhsAdminBoardAdminList");
		return boardAdminList;
	}
	
	@Override
	public int selectBoardCommCnt(BoardComm board) {
		int boardCommCnt = session.selectOne("LhsAdminBoardCommCnt",board);
		return boardCommCnt;
	}
	
	@Override
	public List<BoardComm> selectBoardCommList(BoardComm board) {
		List<BoardComm> boardCommList = session.selectList("LhsAdminBoardCommList", board);
		return boardCommList;
	}
	
	@Override
	public BoardComm selectBoard(BoardComm board) {
		BoardComm detailBoard = session.selectOne("LhsAdminBoardOne", board);
		System.out.println(detailBoard);
		return detailBoard;
	}
	
	@Override
	public int deleteBoard(BoardComm board) {
		int result = session.delete("LhsAdminBoardDelete", board);
		return result;
	}
	
	@Override
	public int selectUsersCnt(User user) {
		int result = session.selectOne("LhsUsersCnt",user);
		return result;
	}
	
	@Override
	public List<User> selectUsersList(User user) {
		List<User> userList = session.selectList("LhsUsersList", user);
		return userList;
	}

}
