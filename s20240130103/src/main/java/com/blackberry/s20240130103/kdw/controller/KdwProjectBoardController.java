package com.blackberry.s20240130103.kdw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.service.KdwProjectBoardPaging;
import com.blackberry.s20240130103.kdw.service.KdwProjectBoardService;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KdwProjectBoardController {
	
	private final KdwProjectBoardService pBoardService;
	
	// 프로젝트 공유게시판 리스트
	@GetMapping(value = "boardProject")
	public String getProjectBoardList(HttpServletRequest request, Model model, 
			@RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type)  {
		System.out.println("projectBoardController getProjectBoardList Start...");
		
		// 글작성 유저넘버(로그인유저)
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController getProjectBoardList userNo: " +  userNo);
		// 글작성하는 프로젝트 넘버
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController getProjectBoardList projectNo: " +  projectNo);
		
		// 게시글 수 가져오기
		int totPboardListCnt = 0;
		// 검색한 게시글 개수
	    if (keyword != null && !keyword.isEmpty()) {
	    	totPboardListCnt = pBoardService.searchPboardListCnt(userNo, projectNo, keyword, type);
	    } else {
	    	totPboardListCnt = pBoardService.totPboardListCnt(userNo, projectNo);
	    }
	    
	    // 페이징 처리
		KdwProjectBoardPaging page = new KdwProjectBoardPaging(totPboardListCnt, currentPage);
		
		List<BoardProject> pboardList;
		// 검색한 게시글 리스트
	    if (keyword != null && !keyword.isEmpty()) {
	    	pboardList = pBoardService.searchProjectBoardList(userNo, projectNo, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	pboardList = pBoardService.getProjectBoardList(userNo, projectNo, page.getStart(), page.getEnd());
	    }

		model.addAttribute("userNo", userNo);
		model.addAttribute("projectNo", projectNo);
		model.addAttribute("totPboardListCnt", totPboardListCnt);
		model.addAttribute("pboardList", pboardList);
	    model.addAttribute("page", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);
		
		return "kdw/projectBoard";
	}
	
	// 쪽지쓰기 페이지로 이동(쪽지쓰기 버튼)
	@GetMapping(value = "projectBoardWrite")
	public String msgWritePage(HttpServletRequest request, Model model) {
		System.out.println("MsgController msgWritePage Start...");
		// 세션에서 보내는 사람의 아이디 가져오기
		Long senderId = (Long) request.getSession().getAttribute("user_no");

		// 유저 테이블에서 모든 사용자 목록 가져오기
		// List<User> userList = pBoardService.getAllUsers();

		// 워크스페이스 페이지에서 요청하는 받는사람(닉네임+아이디로 인풋 입력)
		String userNoParam = request.getParameter("user_no");
		Long userNo = null;
		User userNicId = null;

		System.out.println("MsgController msgWritePage userNoParam: " + userNoParam);
		// 조회수 페이지
		model.addAttribute("senderId", senderId);
		model.addAttribute("userNo", userNo);

		return "kdw/projectBoardWrite";
	}
	
}
