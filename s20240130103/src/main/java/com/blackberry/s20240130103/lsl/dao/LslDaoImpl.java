package com.blackberry.s20240130103.lsl.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LslDaoImpl implements LslDao {

	private final SqlSession session;

	@Override
	public int freeBoardWrite(LslBoardComm lslBoardComm, Long user_no) {
	    System.out.println("LslDaoImpl freeBoardWrite Start...");
	    
	    // 게시글 삽입
	    int insertResult = session.insert("lslFreeBoardWrite", lslBoardComm);
	    
	    // 파일 정보 삽입
	    if (insertResult > 0) {
	        // 게시글이 성공적으로 삽입되었을 때만 파일 정보를 삽입
	        int fileInsertResult = session.insert("lslFreeBoardFile", lslBoardComm);
	        if (fileInsertResult <= 0) {
	            // 파일 정보 삽입 실패 처리
	           
	        }
	    } else {
	       
	    }
	    
	    return insertResult;
	}

	

}
