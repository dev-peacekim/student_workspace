package com.blackberry.s20240130103.kdw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KdwProjectBoardController {
	// 프로젝트 공유게시판 리스트
	@GetMapping(value = "boardProject")
	public String getProjectBoardList(HttpServletRequest request, Model model) {
		System.out.println("projectBoardController getProjectBoardList Start...");
		
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController getProjectBoardList userNo: " +  userNo);
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController getProjectBoardList projectNo: " +  projectNo);
		
		model.addAttribute("userNo", userNo);
		model.addAttribute("projectNo", projectNo);
		
		return "kdw/projectBoard";
	}
	
}
