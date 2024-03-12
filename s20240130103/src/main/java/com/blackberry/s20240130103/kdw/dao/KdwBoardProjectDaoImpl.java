package com.blackberry.s20240130103.kdw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class KdwBoardProjectDaoImpl implements KdwBoardProjectDao {
	
	private final SqlSession session;
	
	// 게시글 개수
	@Override
	public int totPboardListCnt(Long userNo) {
        int totPboardListCnt = 0;
        try {
        	totPboardListCnt = session.selectOne("totPboardListCnt", userNo);
            System.out.println("KdwBoardProjectDaoImpl totPboardListCnt totReceiveMsgCnt->" + totPboardListCnt);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("KdwBoardProjectDaoImpl totPboardListCnt Exception ->" + e.getMessage());
        }
        return totPboardListCnt;
	}

	@Override
	public List<BoardProject> getProjectBoardList(Long userNo, int start, int end) {
	    List<BoardProject> pboardList = null;
	    System.out.println("KdwBoardProjectDaoImpl getProjectBoardList start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("userNo", userNo);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        pboardList = session.selectList("kdwPboardListAll", paramMap);
	        System.out.println("KdwBoardProjectDaoImpl getProjectBoardList pboardList.size()->" + pboardList.size());
	        System.out.println("KdwBoardProjectDaoImpl getProjectBoardList start&end" + + start +", "+ end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("KdwBoardProjectDaoImpl getProjectBoardList e.getMessage()->" + e.getMessage());
	    }

	    return pboardList;
	}

}
