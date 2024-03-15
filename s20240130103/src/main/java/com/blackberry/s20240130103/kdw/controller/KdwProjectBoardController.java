package com.blackberry.s20240130103.kdw.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.Message;
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
	
	// 게시글 쓰기 페이지로 이동(쪽지쓰기 버튼)
	@GetMapping(value = "projectBoardWrite")
	public String pboardWritePage(HttpServletRequest request, Model model) {
		System.out.println("projectBoardController pboardWritePage Start...");
		
		 // 글작성 유저넘버(로그인유저) 
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController pboardWritePage userNo: " + userNo); // 글작성하는 프로젝트 넘버 
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController pboardWritePage projectNo: " +projectNo);
		 
		model.addAttribute("userNo", userNo);
		model.addAttribute("projectNo", projectNo);
		return "kdw/projectBoardWrite";
	}
	
	/*============== 업로드 글작성 ==============*/
    // 글쓰기 - 멀티 업로드(보내기 버튼)
	@ResponseBody
    @PostMapping(value = "writeSave")
    public String writeSave (BoardProject boardProject, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

    	System.out.println("projectBoardController writeSave boardProject : " + boardProject);
        try {
    		// 글작성 유저넘버(로그인유저)
    		Long userNo = (Long) request.getSession().getAttribute("user_no");
    		System.out.println("projectBoardController getProjectBoardList userNo: " +  userNo);
    		// 글작성하는 프로젝트 넘버
    		Long projectNo = Long.parseLong(request.getParameter("project_no"));
    		System.out.println("projectBoardController getProjectBoardList projectNo: " +  projectNo);
    		
			// 업로드 경로 설정
            String path = request.getSession().getServletContext().getRealPath("/upload/boardProjectFile/");
            boardProject.setUser_no(userNo);
            boardProject.setProject_no(projectNo);
            
            pBoardService.writeSave(boardProject, files, path); // 메시지 전송 서비스 호출

            return "Message sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("projectBoardController writeSave Exception ->" + e.getMessage());
            return "Error sending message";
        }
    }

    
	
	
}
