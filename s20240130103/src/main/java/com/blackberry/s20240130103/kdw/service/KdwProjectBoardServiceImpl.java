package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.kdw.dao.KdwBoardProjectDao;
import com.blackberry.s20240130103.kdw.model.BoardProject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KdwProjectBoardServiceImpl implements KdwProjectBoardService {
	
	private final KdwBoardProjectDao pboardDao;
	
	// 게시글 개수
	@Override
	public int totPboardListCnt(Long userNo) {
		log.info("KdwProjectBoardServiceImpl totPboardListCnt start...");
		int totPboardListCnt = pboardDao.totPboardListCnt(userNo);
		log.info("KdwProjectBoardServiceImpl totPboardListCnt totPboardListCnt->" + totPboardListCnt);
		return totPboardListCnt;
	}
	// 게시글 리스트
	@Override
	public List<BoardProject> getProjectBoardList(Long userNo, int start, int end) {
	    log.info("KdwProjectBoardServiceImpl getProjectBoardList start...");
	    List<BoardProject> pboardList = pboardDao.getProjectBoardList(userNo, start, end);
	    System.out.println("KdwProjectBoardServiceImpl getProjectBoardList pboardList.size(): " + pboardList.size());
	    return pboardList;
	}
	
	

}
