package com.blackberry.s20240130103.kdw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.service.KdwProjectBoardService;
import com.blackberry.s20240130103.kdw.service.MsgPaging;

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
			@RequestParam(name = "currentPage", defaultValue = "1") String currentPage) {
		System.out.println("projectBoardController getProjectBoardList Start...");
		
		// 글작성 유저넘버(로그인유저)
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController getProjectBoardList userNo: " +  userNo);
		// 글작성하는 프로젝트 넘버
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController getProjectBoardList projectNo: " +  projectNo);
		
		// 게시글 수 가져오기
		int totPboardListCnt = 0;
		totPboardListCnt = pBoardService.totPboardListCnt(userNo);
		
	    // 페이징 처리
		MsgPaging page = new MsgPaging(totPboardListCnt, currentPage);
		List<BoardProject> pboardList;
		pboardList = pBoardService.getProjectBoardList(userNo, page.getStart(), page.getEnd());
		
		
		model.addAttribute("userNo", userNo);
		model.addAttribute("totPboardListCnt", totPboardListCnt);
		model.addAttribute("pboardList", pboardList);
		model.addAttribute("projectNo", projectNo);
		
		return "kdw/projectBoard";
	}
	
}
