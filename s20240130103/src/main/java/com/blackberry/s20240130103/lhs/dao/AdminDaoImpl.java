package com.blackberry.s20240130103.lhs.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
	
	private final SqlSession session;

	@Override
	public Map<String, Long> selectTablesCnt() {
		Map<String, Long> tableCntMap = session.selectMap(null, null);
		return null;
	}

}
/*
 * 
쿼리문
SELECT u.all_user_cnt,p.all_project_cnt,bcr.all_coment_cnt,bc.all_board_cnt,tu.today_user_cnt,tp.today_project_cnt,tbcr.today_coment_cnt,tbc.today_board_cnt
FROM (SELECT count(*) all_project_cnt FROM PROJECT p WHERE p.PROJECT_DELETE_CHK =0) p,
	(SELECT count(*) all_user_cnt FROM users u WHERE u.USER_DELETE_CHK = 0) u,
	(SELECT count(*) all_coment_cnt FROM BOARD_COMM_REPLY bcr WHERE BCR.CREPLY_DELETE_CHK = 0) bcr,
	(SELECT count(*) all_board_cnt FROM BOARD_COMM bc WHERE bc.CBOARD_DELETE_CHK = 0) bc,
    (select count(*) today_user_cnt from users u where u.user_delete_chk=0 and TRUNC(u.user_date) = TRUNC(SYSDATE)) tu,
    (select count(*) today_project_cnt from project p where p.project_delete_chk=0 and TRUNC(p.project_date) = TRUNC(SYSDATE)) tp,
    (select count(*) today_coment_cnt from BOARD_COMM_REPLY bcr where bcr.creply_delete_chk=0 and TRUNC(bcr.creply_date) = TRUNC(SYSDATE)) tbcr,
    (select count(*) today_board_cnt FROM BOARD_COMM bc where bc.cboard_delete_chk=0 and TRUNC(bc.cboard_date) = TRUNC(SYSDATE)) tbc;
 * */