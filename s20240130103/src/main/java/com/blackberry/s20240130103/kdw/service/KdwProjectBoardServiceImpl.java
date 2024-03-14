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
	public int totPboardListCnt(Long userNo, Long projectNo) {
		log.info("KdwProjectBoardServiceImpl totPboardListCnt start...");
		int totPboardListCnt = pboardDao.totPboardListCnt(userNo, projectNo);
		log.info("KdwProjectBoardServiceImpl totPboardListCnt totPboardListCnt->" + totPboardListCnt);
		return totPboardListCnt;
	}
	// 게시글 리스트
	@Override
	public List<BoardProject> getProjectBoardList(Long userNo, Long projectNo, int start, int end) {
	    log.info("KdwProjectBoardServiceImpl getProjectBoardList start...");
	    List<BoardProject> pboardList = pboardDao.getProjectBoardList(userNo, projectNo, start, end);
	    System.out.println("KdwProjectBoardServiceImpl getProjectBoardList pboardList.size(): " + pboardList.size());
	    return pboardList;
	}
	
	// ! ========== 검색기능 ========== !
	// 검색한 게시글 개수
	@Override
	public int searchPboardListCnt(Long userNo, Long projectNo, String keyword, String type) {
		log.info("KdwProjectBoardServiceImpl searchPboardListCnt start...");
		int searchPboardListCnt = pboardDao.searchPboardListCnt(userNo, projectNo, keyword, type);
		log.info("KdwProjectBoardServiceImpl searchPboardListCnt Cnt->" + searchPboardListCnt);
		return searchPboardListCnt;
	}
	// 검색한 게시글 리스트
	@Override
	public List<BoardProject> searchProjectBoardList(Long userNo, Long projectNo, String keyword, String type, int start, int end) {
	    log.info("KdwProjectBoardServiceImpl searchProjectBoardList start...");
	    List<BoardProject> searchProjectBoardList = pboardDao.searchProjectBoardList(userNo, projectNo, keyword, type, start, end);
	    System.out.println("KdwProjectBoardServiceImpl searchProjectBoardList.size(): " + searchProjectBoardList.size());
	    return searchProjectBoardList;
	}
	
	

}
